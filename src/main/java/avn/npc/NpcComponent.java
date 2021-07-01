package avn.npc;

import avn.UnitComponent;
import avn.event.NpcReachGoalEvent;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import avn.AnimalVsNpcType;
import avn.Config;

import static com.almasb.fxgl.dsl.FXGL.*;

import java.util.List;

import com.almasb.fxgl.entity.Entity;

public abstract class NpcComponent extends UnitComponent{
	protected double speed;
	boolean stop = false;
	boolean animalInRange = false;
	public NpcComponent(int atk, int hp, int range, int cd, double speed, String imageName) {
		super(atk, hp, range, cd, imageName);
		this.speed = speed;
	}
	@Override
	protected void move(double tpf) {
		Point2D p = entity.getPosition();
		Rectangle2D r = new Rectangle2D(p.getX() - Range*80, p.getY()-1, p.getX(), 2);
		List<Entity> entities = getGameWorld().getEntitiesInRange(r);
		for (Entity entity : entities) {
			if (entity.isType(AnimalVsNpcType.ANIMAL)) {
				animalInRange = true;
				stop = true;
				return;
			}
		}
		animalInRange = false;
		stop = false;
		if (!stop) {
			entity.translateX(-speed * tpf * 60);
			if (entity.getPosition().getX() <= Config.spawnPointX[0]) {
				getEventBus().fireEvent(new NpcReachGoalEvent());
			}
		}
	}
	public void setStop() {
		stop = true;
	}
	public void releaseStop() {
		stop = false;
	}
}
