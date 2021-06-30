package avn;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.texture.Texture;

import avn.animal.AnimalComponent;
import avn.animal.AnimalIcon;
import avn.animal.BlueBirdComponent;
import avn.collision.AnimalNpcHandler;
import avn.collision.BulletAnimalHandler;
import avn.npc.BirdHunterComponent;
import avn.npc.NpcComponent;
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

    List<NpcComponent> npcComponents = new ArrayList<>();
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
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new AnimalNpcHandler());
        getPhysicsWorld().addCollisionHandler(new BulletAnimalHandler());
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
        // TODO: load npc components
        npcComponents.add(new BirdHunterComponent());

        // schedule the occurrence of npcs
        run(() -> {
            int i = random(0, npcComponents.size()-1);
            int row = random(0, 4);
            spawnNpc(npcComponents.get(i), row);
        }, Duration.seconds(3));
    }
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("eggs", 2);
    }
    @Override
    protected void initInput() {
        Input input = getInput();
        input.addAction(new UserAction("Place Animal") {
            private Rectangle2D worldBounds = new Rectangle2D(45, 114, 895, 589);
            @Override
            protected void onActionBegin() {
                // TODO: check eggs >= cost
                if (selected != null && worldBounds.contains(input.getMousePositionWorld())) {
                    int i = (int)(input.getMousePositionWorld().getX() - 45) / 99;
                    int j = (int)(input.getMousePositionWorld().getY() - 114) / 118;
                    if (!isOccupied[i][j]) {
                        int cost = selected.getCost();
                        inc("eggs", -cost);
                        placeAnimal(Config.spawnPointX[i], Config.spawnPointY[j]);
                        isOccupied[i][j] = true;
                        selected = null;
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
        Text uiEggs = new Text("");
        uiEggs.setFont(Font.font(20));
        uiEggs.setTranslateX(34);
        uiEggs.setTranslateY(100);
        uiEggs.textProperty().bind(getip("eggs").asString());
        getGameScene().addUINode(uiEggs);
    }
    private void placeAnimal(int x, int y) {
        SpawnData spawnData = new SpawnData(x, y)
                .put("selected", selected);
        getGameWorld().spawn("Animal", spawnData);
    }
    private void spawnNpc(NpcComponent nc, int row) {
        SpawnData spawnData = new SpawnData(Config.npcSpawnPoints[row])
                .put("component", nc);
        getGameWorld().spawn("Npc", spawnData);
    }
    public static void main(String[] args) {
        launch(args);
    }
}