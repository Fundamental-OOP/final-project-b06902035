package avn.npc;


import java.util.function.DoubleUnaryOperator;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;

import javafx.geometry.Point2D;
import javafx.util.Duration;


import com.almasb.fxgl.time.LocalTimer;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import static com.almasb.fxgl.dsl.FXGL.*;


public class MissionaryComponent extends NpcComponent{

    private AnimationChannel animWalk, animAttack;

    public MissionaryComponent(){
        super(50, 200, 1, 2, 0.1, "");
        
        animWalk = new AnimationChannel(FXGL.image("Missionary.png"), 1, 90, 97, Duration.seconds(1), 0, 0);
        animAttack = new AnimationChannel(FXGL.image("Missionary.png"), 1, 90, 97, Duration.seconds(1), 0, 0);
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
