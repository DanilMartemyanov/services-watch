package ru.itis.javafxgui.event.source;

import javafx.scene.control.Button;
import ru.itis.javafxgui.event.PausingVideoEvent;

public class PausingVideoEventSource {
    private static Button eventTarget;

    public PausingVideoEventSource(Button eventTarget) {
        PausingVideoEventSource.eventTarget = eventTarget;
    }

    public static void fireEvent() {
        eventTarget.fireEvent(new PausingVideoEvent());
    }
}
