package avn.animal;
import avn.UnitComponent;


public abstract class AnimalComponent extends UnitComponent{
	int Cost;
	public AnimalComponent(int atk, int hp, int range, int cd, int cost, String imageName) {
		super(atk, hp, range, cd, imageName);
		Cost = cost;
	}
	public int getCost() {
		return Cost;
	}
}
