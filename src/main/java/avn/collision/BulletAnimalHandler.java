package avn.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import avn.AnimalVsNpcType;
import avn.util.Helper;
import static com.almasb.fxgl.dsl.FXGL.*;

public class BulletAnimalHandler extends CollisionHandler{
	public BulletAnimalHandler() {
		super(AnimalVsNpcType.BULLET, AnimalVsNpcType.ANIMAL);
	}
	@Override
	protected void onCollisionBegin(Entity bullet, Entity animal) {
		int damage = bullet.getInt("damage");
		bullet.removeFromWorld();
		Helper.changeEntityHP(animal, -damage);
		getAudioPlayer().playSound(getAssetLoader().loadSound("BulletHit.mp3"));
    }
}
