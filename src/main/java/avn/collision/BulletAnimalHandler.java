package avn.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import avn.AnimalVsNpcType;

public class BulletAnimalHandler extends CollisionHandler{
	public BulletAnimalHandler() {
		super(AnimalVsNpcType.BULLET, AnimalVsNpcType.ANIMAL);
	}
	@Override
	protected void onCollisionBegin(Entity bullet, Entity animal) {
		bullet.removeFromWorld();
		
    }
}
