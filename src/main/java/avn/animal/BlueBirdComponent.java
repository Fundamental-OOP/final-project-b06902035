package avn.animal;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.time.LocalTimer;

import javafx.geometry.Point2D;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class BlueBirdComponent extends AnimalComponent{
	public BlueBirdComponent() {
		// STR, HP, Range, CD, Cost
		super(0, 200, 0, 10, 1, "BlueBird.jpg");
	}
	@Override
	public void performAttack() {
		Point2D position = getEntity().getPosition();
		int x = (int)position.getX() + 20;
		int y = (int)position.getY() + 20;
		SpawnData spawnData = new SpawnData(x, y);
        FXGL.spawn("BirdEgg", spawnData);
	}
}
