package avn.event;

import com.almasb.fxgl.entity.Entity;

import javafx.event.Event;
import javafx.event.EventType;

public class UnitDieEvent extends Event{
	public static final EventType<UnitDieEvent> ANY
            = new EventType<>(Event.ANY, "UNIT_KILLED");

    private Entity unit;
	public Entity getUnit() {
		return unit;
	}
	public UnitDieEvent(Entity unit) {
		super(ANY);
		this.unit = unit;
	}
}
