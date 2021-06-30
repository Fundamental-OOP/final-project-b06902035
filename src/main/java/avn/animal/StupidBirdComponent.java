package avn.animal;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.time.LocalTimer;

import javafx.geometry.Point2D;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class StupidBirdComponent extends AnimalComponent{
	public StupidBirdComponent() {
		// STR, HP, Range, CD, Cost
		super(25, 600, 1, 2, 1, "StupidBird.png");
	}
	@Override
	protected void performAttack() {
		Point2D position = entity.getPosition().add(80, 40);
                
    }
}
