package avn.npc;

import avn.components.UnitComponent;

public abstract class NpcComponent extends UnitComponent{
	protected double speed;
	boolean stop = false;
	public NpcComponent(int atk, int hp, int range, int cd, double speed, String imageName) {
		super(atk, hp, range, cd, imageName);
		this.speed = speed;
	}
	@Override
	protected void move(double tpf) {
		if (!stop) {
			entity.translateX(-speed * tpf * 60);
		}
	}
	public void setStop() {
		stop = true;
	}
	public void releaseStop() {
		stop = false;
	}
}
