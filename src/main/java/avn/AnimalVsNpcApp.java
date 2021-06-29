package avn;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.texture.Texture;

import avn.animal.AnimalComponent;
import avn.animal.AnimalIcon;
import avn.animal.BlueBirdComponent;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * This is an example of a tower defense game.
 *
 * Demo:
 * 1. Enemies move using waypoints
 * 2. Player can place towers
 * 3. Towers can shoot enemies
 * 4. Game ends if enemies are dead or have reached the last waypoint
 *
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class AnimalVsNpcApp extends GameApplication {

    List<AnimalComponent> animalComponents = new ArrayList<>();
    AnimalComponent selected;
    // the selected animal component
    // change this when clicking menu icons
    boolean[][] isOccupied = new boolean[9][5];

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("AnimalVsNpc");
        settings.setWidth(1000);
        settings.setHeight(752);
        settings.setGameMenuEnabled(true);
    }
    @Override
    protected void initGame() {
        // add factory
        getGameWorld().addEntityFactory(new AnimalVsNpcFactory());
        // set background pic and music
        getGameScene().setBackgroundRepeat("mainBG.png");
        loopBGM("Grasswalk.mp3");
        // TODO: load animal component of newly added animals
        animalComponents.add(new BlueBirdComponent());
    }
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("energy", 50);
    }
    @Override
    protected void initInput() {
        Input input = getInput();
        input.addAction(new UserAction("Place Animal") {
            private Rectangle2D worldBounds = new Rectangle2D(45, 114, 895, 589);
            @Override
            protected void onActionBegin() {
                if (selected != null && worldBounds.contains(input.getMousePositionWorld())) {
                    int i = (int)(input.getMousePositionWorld().getX() - 45) / 99;
                    int j = (int)(input.getMousePositionWorld().getY() - 114) / 118;
                    if (!isOccupied[i][j]) {
                        placeAnimal(Config.spawnPointX[i], Config.spawnPointY[j]);
                        isOccupied[i][j] = true;
                    }
                }
            }
        }, MouseButton.PRIMARY);
    }

    @Override
    protected void initUI() {
        // set icon on the top memu bar
        for (int i = 0; i < animalComponents.size(); i++) {
            AnimalComponent ac = animalComponents.get(i);
            String name = ac.getImageName();
            AnimalIcon icon = new AnimalIcon(name);
            icon.setTranslateX(110 + i * 80);
            icon.setTranslateY(11);
            icon.setOnMouseClicked(e -> {
                selected = ac;
            });
            getGameScene().addUINode(icon);
        }
        // set text bar to show enery
        Text uiEnergy = new Text("");
        uiEnergy.setFont(Font.font(20));
        uiEnergy.setTranslateX(34);
        uiEnergy.setTranslateY(100);
        uiEnergy.textProperty().bind(getip("energy").asString());
        getGameScene().addUINode(uiEnergy);
    }
    private void placeAnimal(int x, int y) {
        SpawnData spawnData = new SpawnData(x, y)
                .put("selected", selected);
        getGameWorld().spawn("Animal", spawnData);
    }
    public static void main(String[] args) {
        launch(args);
    }
}