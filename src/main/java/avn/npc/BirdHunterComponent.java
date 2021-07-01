package avn.npc;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;

import javafx.geometry.Point2D;

//
import com.almasb.fxgl.time.LocalTimer;
import static com.almasb.fxgl.dsl.FXGL.*;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;
//

public class BirdHunterComponent extends NpcComponent{

    private AnimationChannel animWalk, animAttack;

	public BirdHunterComponent() {
		super(50, 200, 3, 2, 0.1, "");
        animWalk = new AnimationChannel(FXGL.image("HunterWalk_176x80.png"), 2, 88, 80, Duration.seconds(0.8), 0, 1);
        animAttack = new AnimationChannel(FXGL.image("HunterAttack_174x80.png"), 2, 87, 80, Duration.seconds(0.5), 0, 1);
        this.texture = new AnimatedTexture(animWalk);
        this.texture.loop();
	}
	@Override
    public void onAdded() {
        timer = newLocalTimer();
		timer.capture();
        // for animation:
        entity.getViewComponent().addChild(texture);
    }
	@Override
    public void onUpdate(double tpf) {
		move(tpf);
		if (timer.elapsed(CD)) {
			performAttack();
			timer.capture();
            // for animation:
            this.texture.loopAnimationChannel(animAttack);
		}
        else if (texture.getAnimationChannel() != animWalk){
            // for animation:
            getGameTimer().runOnceAfter(() -> {
                // code to run once after 0.5 second
                this.texture.loopAnimationChannel(animWalk);
            }, Duration.seconds(0.5));
        }
		performskill();
	}
	@Override
	protected void performAttack() {
		if (animalInRange) {
			Point2D position = entity.getPosition().add(0, 40);
			Point2D direction = new Point2D(-1, 0);
			SpawnData data = new SpawnData(position)
								.put("direction", direction)
								.put("damage", ATK);
			FXGL.spawn("Bullet", data);
		}
	}
}
