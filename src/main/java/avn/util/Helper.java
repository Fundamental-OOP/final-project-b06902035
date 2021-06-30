package avn.util;

import java.util.List;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

import avn.UnitComponent;
import avn.animal.AnimalComponent;
import avn.npc.NpcComponent;
import javafx.geometry.Point2D;

public class Helper {
	public static <T extends Component> T getSubclassComponent(Entity e, Class<T> c) {
		List<Component> components = e.getComponents();
		for (Component component : components) {
			if (c.isInstance(component)) {
				return c.cast(component);
			}
		}
		return null;
	}
	public static NpcComponent getNpcComponent(Entity e) {
		List<Component> components = e.getComponents();
		for (Component c : components) {
			if (c instanceof NpcComponent) {
				return (NpcComponent)c;
			}
		}
		return null;
	}
	public static AnimalComponent getAnimalComponent(Entity e) {
		List<Component> components = e.getComponents();
		for (Component c : components) {
			if (c instanceof AnimalComponent) {
				return (AnimalComponent)c;
			}
		}
		return null;
	}
	public static UnitComponent getUnitComponent(Entity e) {
		List<Component> components = e.getComponents();
		for (Component c : components) {
			if (c instanceof UnitComponent) {
				return (UnitComponent)c;
			}
		}
		return null;
	}
	public static void changeEntityHP(Entity e, int amount) {
		UnitComponent uc = getUnitComponent(e);
		if (uc != null) {
			uc.changeHP(amount);
		}
		else {
			System.out.println(e + "is not a unit");
		}
	}
	public static int[] getGridFromPoint(Point2D p) {
		int i = (int)(p.getX() - 45) / 99;
        int j = (int)(p.getY() - 114) / 118;
		return new int[] {i, j};
	}
}
