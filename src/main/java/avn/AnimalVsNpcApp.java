package avn;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;

import avn.animal.*;
import avn.collision.AnimalNpcHandler;
import avn.collision.BulletAnimalHandler;
import avn.collision.DuckEggNpcHandler;
import avn.event.NpcReachGoalEvent;
import avn.event.UnitDieEvent;
import avn.npc.BirdHunterComponent;
import avn.npc.MissionaryComponent;
import avn.npc.NpcComponent;
import avn.util.Helper;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class AnimalVsNpcApp extends GameApplication {

    List<NpcComponent> npcComponents = new ArrayList<>();
    List<AnimalComponent> animalComponents = new ArrayList<>();
    AnimalComponent selected;
    // the selected animal component
    // change this when clicking menu icons
    boolean[][] isOccupied = new boolean[10][5];

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
        getPhysicsWorld().addCollisionHandler(new DuckEggNpcHandler());
    }
    @Override
    protected void initGame() {
        // set cursor to default
        getGameScene().setCursor(Cursor.DEFAULT);
        // add factory
        getGameWorld().addEntityFactory(new AnimalVsNpcFactory());
        // set background pic and music
        getGameScene().setBackgroundRepeat("mainBG.png");
        loopBGM("Grasswalk.mp3");
        // TODO: load animal component of newly added animals
        animalComponents.add(new BlueBirdComponent());
        animalComponents.add(new DuckComponent());
        animalComponents.add(new StupidBirdComponent());
        // TODO: load npc components
        npcComponents.add(new BirdHunterComponent());
        npcComponents.add(new MissionaryComponent());

        // set event handlers
        getEventBus().addEventHandler(UnitDieEvent.ANY, this::onUnitDie);
        getEventBus().addEventHandler(NpcReachGoalEvent.ANY, e -> gameOver());

        // schedule the occurrence of npcs
        run(() -> {
            int i = random(0, npcComponents.size()-1);
            int row = random(0, 4);
            spawnNpc(npcComponents.get(i), row);
        }, Duration.seconds(10));
    }
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("eggs", 2);
        vars.put("isOccupied", isOccupied);
    }
    @Override
    protected void initInput() {
        Input input = getInput();
        input.addAction(new UserAction("Place Animal") {
            private Rectangle2D worldBounds = new Rectangle2D(45, 114, 895, 589);
            @Override
            protected void onActionBegin() {
                int cost;
                try {
                    cost = selected.getCost();
                } catch (Exception e) {
                    return;
                }
                if (geti("eggs") < cost) {
                    selected = null;
                    getGameScene().setCursor(Cursor.DEFAULT);
                }
                else if (selected != null && worldBounds.contains(input.getMousePositionWorld())) {
                    int[] grid = Helper.getGridFromPoint(input.getMousePositionWorld());
                    int i = grid[0];
                    int j = grid[1];
                    if (!isOccupied[i][j]) {
                        inc("eggs", -cost);
                        placeAnimal(Config.spawnPointX[i], Config.spawnPointY[j]);
                        isOccupied[i][j] = true;
                        selected = null;
                        getGameScene().setCursor(Cursor.DEFAULT);
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
                getGameScene().setCursor(Cursor.HAND);
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
    
    private void onUnitDie(UnitDieEvent event) {
        Entity unit = event.getUnit();
        if (unit.getType() == AnimalVsNpcType.ANIMAL) {
        }
    }

    private void gameOver() {
        getDialogService().showMessageBox("Game Over!!!", getGameController()::exit);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
