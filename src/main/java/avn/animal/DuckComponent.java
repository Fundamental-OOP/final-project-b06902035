package avn.animal;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import javafx.geometry.Point2D;


public class DuckComponent extends AnimalComponent{
	public DuckComponent() {
		// STR, HP, Range, CD, Cost
		super(50, 200, 10000, 2, 2, "Duck.jpg");
	}
	@Override
	protected void performAttack() {
		Point2D position = entity.getPosition().add(80, 40);
		Point2D direction = new Point2D(1, 0);
		SpawnData data = new SpawnData(position)
							.put("direction", direction)
							.put("damage", ATK);
		FXGL.spawn("DuckEgg", data);
	}
}
