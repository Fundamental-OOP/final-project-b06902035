package avn.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
//import com.almasb.fxgl.dsl.FXGL;

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
		//FXGL.play("DuckEggHit.mp3");
		Helper.changeEntityHP(npc, -damage);
    }
}
