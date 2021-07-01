package avn.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import avn.AnimalVsNpcType;
import avn.util.Helper;

public class DuckEggNpcHandler extends CollisionHandler{
	public DuckEggNpcHandler() {
		super(AnimalVsNpcType.DUCKEGG, AnimalVsNpcType.NPC);
	}
	@Override
	protected void onCollisionBegin(Entity duckEgg, Entity npc) {
		int damage = duckEgg.getInt("damage");
		duckEgg.removeFromWorld();
		Helper.changeEntityHP(npc, -damage);
    }
}
