package ru.itis.javafxgui.event.source;

import javafx.scene.control.Button;
import ru.itis.javafxgui.event.ChoosingVideoEvent;
import ru.itis.protocol.item.VideoItem;

public class ChoosingVideoEventSource {
    private static Button eventTarget;

    public ChoosingVideoEventSource(Button eventTarget) {
        ChoosingVideoEventSource.eventTarget = eventTarget;
    }

    public static void fireEvent(VideoItem video) {
        eventTarget.fireEvent(new ChoosingVideoEvent(video));
    }
}
