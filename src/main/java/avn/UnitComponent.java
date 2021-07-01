package avn;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;

import avn.event.UnitDieEvent;
import avn.util.Helper;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public abstract class UnitComponent extends Component{
	protected int ATK, HP, Range;
	protected LocalTimer timer;
	protected Duration CD;
	boolean hasDoubled = false;
	String imageName;
	public UnitComponent(int atk, int hp, int range, int cd, String name) {
		ATK = atk;
		HP = hp;
		Range = range;
		CD = Duration.seconds(cd);
		imageName = name;
	}
	public String getImageName() {
		return imageName;
	}
	public void doubleCD() {
		if (!hasDoubled) {
			CD.multiply(2);
			timer = newLocalTimer();
			timer.capture();
		}
	}
	@Override
    public void onAdded() {
        timer = newLocalTimer();
		timer.capture();
    }
	@Override
    public void onUpdate(double tpf) {
		move(tpf);
		if (timer.elapsed(CD)) {
			performAttack();
			timer.capture();
		}
		performskill();
	}
	protected void move(double tpf) {};
	protected void performAttack() {};
	protected void performskill() {};
	public void changeHP(int amount) {
		HP += amount;
		if (HP <= 0)
			die(); 
	}
	protected void die() {
		// TODO: add dieing animation
		getEventBus().fireEvent(new UnitDieEvent(entity));
		boolean[][] isOccupied = geto("isOccupied");
		int[] grid = Helper.getGridFromPoint(entity.getPosition().add(40, 40));
		isOccupied[grid[0]][grid[1]] = false;
		entity.removeFromWorld();
	}
}
