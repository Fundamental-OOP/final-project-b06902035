package avn.event;

import javafx.event.Event;
import javafx.event.EventType;

public class NpcReachGoalEvent extends Event{
	public static final EventType<NpcReachGoalEvent> ANY
            = new EventType<>(Event.ANY, "GameOver");

    public NpcReachGoalEvent() {
        super(ANY);
    }
}
