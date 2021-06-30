package avn;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;

import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public abstract class UnitComponent extends Component{
	protected int ATK, HP, Range;
	protected LocalTimer timer;
	protected Duration CD;
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
		entity.removeFromWorld();
	}
}
