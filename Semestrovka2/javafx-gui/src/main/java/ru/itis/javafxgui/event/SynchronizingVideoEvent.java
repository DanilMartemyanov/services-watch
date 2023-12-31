package ru.itis.javafxgui.event;

import javafx.event.Event;
import javafx.event.EventType;

public class SynchronizingVideoEvent extends Event {
    public static final EventType<SynchronizingVideoEvent> TYPE =
            new EventType<>(Event.ANY, "SYNCHRONIZING_VIDEO_EVENT_TYPE");

    private final int hour;
    private final int minute;
    private final int second;

    public SynchronizingVideoEvent(int hour, int minute, int second) {
        super(TYPE);
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
}
