package avn;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;

import avn.animal.AnimalComponent;
import avn.event.UnitDieEvent;
import avn.util.Helper;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

import java.util.List;

// Adding animation part: texture
import com.almasb.fxgl.texture.AnimatedTexture;

public abstract class UnitComponent extends Component{
	protected int ATK, HP, Range;
	protected LocalTimer timer;
	protected Duration CD;
	boolean hasDoubled = false;

    protected AnimatedTexture texture;

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
	protected Entity selectTarget(){

		Point2D p = this.getEntity().getPosition();
		Rectangle2D searchRange;
		if(this instanceof AnimalComponent)
			searchRange =  new Rectangle2D(p.getX(), p.getY(), Range*85.0, 1.0);
		else
			searchRange =  new Rectangle2D(p.getX() - Range*85.0, p.getY(), Range*85.0, 1.0);


		List<Entity> candidateTarget = getGameWorld().getEntitiesInRange(searchRange);

		for(Entity e : candidateTarget){
			if(this.getEntity().isType(AnimalVsNpcType.ANIMAL) && e.isType(AnimalVsNpcType.NPC))
				return e;
			if(this.getEntity().isType(AnimalVsNpcType.NPC) && e.isType(AnimalVsNpcType.ANIMAL))
				return e;
		}
		return null;
	}

}
