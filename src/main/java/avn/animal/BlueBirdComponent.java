package avn.animal;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import javafx.geometry.Point2D;


public class BlueBirdComponent extends AnimalComponent{
	public BlueBirdComponent() {
		// STR, HP, Range, CD, Cost
		super(0, 200, 0, 20, 1, "BlueBird.jpg");
	}
	@Override
	protected void performAttack() {
		Point2D position = getEntity().getPosition();
		int x = (int)position.getX() + 20;
		int y = (int)position.getY() + 20;
		SpawnData spawnData = new SpawnData(x, y);
        FXGL.spawn("BirdEgg", spawnData);
	}
}
