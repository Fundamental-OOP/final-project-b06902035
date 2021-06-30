package avn.collision;

import java.util.List;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.CollisionHandler;

import avn.AnimalVsNpcType;
import avn.npc.NpcComponent;
import avn.util.Helper;

public class AnimalNpcHandler extends CollisionHandler{

	public AnimalNpcHandler() {
		super(AnimalVsNpcType.ANIMAL, AnimalVsNpcType.NPC);
	}
	@Override
    protected void onCollisionBegin(Entity animal, Entity npc) {
		NpcComponent nc = Helper.getNpcComponent(npc);
		if (nc != null) {
			nc.setStop();
		}
    }
	@Override
	protected void onCollisionEnd(Entity animal, Entity npc) {
		NpcComponent nc = Helper.getNpcComponent(npc);
		if (nc != null) {
			nc.releaseStop();
		}
	}
}
