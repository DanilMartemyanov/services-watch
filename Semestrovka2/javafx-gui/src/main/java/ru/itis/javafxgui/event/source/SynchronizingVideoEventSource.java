package ru.itis.javafxgui.event.source;

import javafx.scene.control.Button;
import ru.itis.javafxgui.event.SynchronizingVideoEvent;

public class SynchronizingVideoEventSource {
    private static Button eventTarget;

    public SynchronizingVideoEventSource(Button eventTarget) {
        SynchronizingVideoEventSource.eventTarget = eventTarget;
    }

    public static void fireEvent(int hour, int minute, int second) {
        eventTarget.fireEvent(new SynchronizingVideoEvent(hour, minute, second));
    }
}
