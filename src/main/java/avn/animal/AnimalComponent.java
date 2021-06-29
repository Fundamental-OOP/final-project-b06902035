package avn.animal;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;

import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public abstract class AnimalComponent extends Component{
	int ATK, HP, Range, Cost;
	LocalTimer timer;
	Duration CD;
	public AnimalComponent(int a, int b, int c, int d, int e) {
		ATK = a;
		HP = b;
		Range = c;
		CD = Duration.seconds(d);
		Cost = e;
	}
	public int getCost() {
		return Cost;
	}
	abstract public String getImageName();

	@Override
    public void onAdded() {
        timer = newLocalTimer();
		timer.capture();
    }
}
