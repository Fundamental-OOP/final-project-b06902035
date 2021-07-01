package avn.animal;

import com.almasb.fxgl.entity.Entity;
import avn.Config;
import avn.MoveComponent;
import avn.npc.NpcComponent;
import avn.util.Helper;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGL.*;

public class StupidBirdComponent extends AnimalComponent{

	public StupidBirdComponent() {
		// STR, HP, Range, CD, Cost
		super(25, 600, 1, 2, 1, "StupidBird.png");
	}
	@Override
	protected void performAttack() {
		Entity target = selectTarget();
		if(target == null)
			return;
		Helper.changeEntityHP(target, -ATK);
    }

	

	@Override
	public void changeHP(int amount) {
		HP += amount;
		if (HP <= 0)
			die(); 
		else if ((HP - amount > 400 && HP <= 400) || 
					(HP - amount > 200 && HP <= 200)) {
			int row = random(0, 4);
			int i = 0;
			boolean[][] isOccupied = geto("isOccupied");
			while (isOccupied[i][row]){
				i += 1;
				if(i==9){
					i=0;
					row = random(0,4);
				}
			}
			// TODO: check if i out of bound
			// if so, get another random row
			int[] grid = Helper.getGridFromPoint(entity.getPosition().add(40, 40));
			isOccupied[grid[0]][grid[1]] = false;
			flyToPosition(i, row);
			isOccupied[i][row] = true;
		}
	}
	
	private void flyToPosition(int i, int row) {
		// TODO: flying animation
		//entity.setPosition(Config.spawnPointX[i], Config.spawnPointY[row]);
		Point2D dst = new Point2D(Config.spawnPointX[i], Config.spawnPointY[row]);
		if (entity.hasComponent(MoveComponent.class)) {
			entity.removeComponent(MoveComponent.class);
		}
		entity.addComponent(new MoveComponent(dst, 400));
	}
}
