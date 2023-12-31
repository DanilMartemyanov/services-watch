package ru.itis.javafxgui.event;

import javafx.event.Event;
import javafx.event.EventType;

public class PausingVideoEvent extends Event {
    public static final EventType<PausingVideoEvent> TYPE =
            new EventType<>(Event.ANY, "PAUSING_VIDEO_EVENT");

    public PausingVideoEvent() {
        super(TYPE);
    }
}
