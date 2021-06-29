package avn.animal;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.time.LocalTimer;

import javafx.geometry.Point2D;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class BlueBirdComponent extends AnimalComponent{

	private LocalTimer timer;
	private Duration interval = Duration.seconds(5);
	@Override
	public String getImageName() {
		return "BlueBird.jpg";
	}
    @Override
    public void onAdded() {
        timer = newLocalTimer();
    }
	@Override
    public void onUpdate(double tpf) {
        if (timer.elapsed(interval)) {
			layEgg();
			timer.capture();
		}
	}
	private void layEgg() {
		Point2D position = getEntity().getPosition();
		int x = (int)position.getX() + 20;
		int y = (int)position.getY() + 20;
		SpawnData spawnData = new SpawnData(x, y);
        FXGL.spawn("BirdEgg", spawnData);
	}
}
