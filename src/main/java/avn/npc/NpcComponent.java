package avn.npc;

import avn.UnitComponent;

public abstract class NpcComponent extends UnitComponent{
	protected double speed;
	public NpcComponent(int atk, int hp, int range, int cd, double speed, String imageName) {
		super(atk, hp, range, cd, imageName);
		this.speed = speed;
	}
}
