package avn.animal;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import javafx.geometry.Point2D;


public class BlueBirdComponent extends AnimalComponent{
	public BlueBirdComponent() {
		// STR, HP, Range, CD, Cost
		super(0, 200, 0, 15, 1, "BlueBird.gif");
	}
	@Override
	protected void performAttack() {
		Point2D position = getEntity().getPosition();
		int x = (int)position.getX() + 40;
		int y = (int)position.getY() + 40;
		SpawnData spawnData = new SpawnData(x, y);
        FXGL.spawn("BirdEgg", spawnData);
	}
}
