package avn.collision;

import java.util.List;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.CollisionHandler;

import avn.AnimalVsNpcType;
import avn.animal.AnimalComponent;
import avn.npc.BirdHunterComponent;
import avn.npc.NpcComponent;

public class AnimalNpcHandler extends CollisionHandler{

	public AnimalNpcHandler() {
		super(AnimalVsNpcType.ANIMAL, AnimalVsNpcType.NPC);
	}
	@Override
    protected void onCollisionBegin(Entity animal, Entity npc) {
		System.out.println("they collude!!!");
		List<Component> components = npc.getComponents();
		for (Component c : components) {
			if (c instanceof NpcComponent) {
				((NpcComponent)c).setStop();
			}
		}
    }
}
