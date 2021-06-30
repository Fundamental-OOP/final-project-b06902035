package avn.npc;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;

import javafx.geometry.Point2D;

public class BirdHunterComponent extends NpcComponent{

	public BirdHunterComponent() {
		super(50, 200, 3, 2, 0.1, "BirdHunter.png");
	}
	@Override
	protected void performAttack() {
		Point2D position = entity.getPosition().add(0, 40);
		Point2D direction = new Point2D(-1, 0);
		SpawnData data = new SpawnData(position).put("direction", direction);
		FXGL.spawn("Bullet", data);
	}
}
