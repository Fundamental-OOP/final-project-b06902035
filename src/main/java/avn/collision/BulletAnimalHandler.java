package avn.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import avn.AnimalVsNpcType;
import avn.util.Helper;

public class BulletAnimalHandler extends CollisionHandler{
	public BulletAnimalHandler() {
		super(AnimalVsNpcType.BULLET, AnimalVsNpcType.ANIMAL);
	}
	@Override
	protected void onCollisionBegin(Entity bullet, Entity animal) {
		int damage = bullet.getInt("damage");
		bullet.removeFromWorld();
		Helper.changeEntityHP(animal, -damage);
    }
}
