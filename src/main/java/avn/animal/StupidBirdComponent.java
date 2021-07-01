package avn.animal;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.time.LocalTimer;

import avn.Config;
import avn.util.Helper;
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
			while (isOccupied[i][row])
				i += 1;
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
		entity.setPosition(Config.spawnPointX[i], Config.spawnPointY[row]);
	}
}
