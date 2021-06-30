package avn.animal;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;

import avn.components.UnitComponent;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

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
