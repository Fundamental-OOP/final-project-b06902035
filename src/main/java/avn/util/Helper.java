package avn.util;

import java.util.List;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

import avn.UnitComponent;
import avn.animal.AnimalComponent;
import avn.npc.NpcComponent;

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
}
