package ru.itis.javafxgui.event;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import ru.itis.protocol.item.VideoItem;

import java.util.List;

public class GettingVideosEventSource {
    private static Button eventTarget;

    public GettingVideosEventSource(Button eventTarget) {
        GettingVideosEventSource.eventTarget = eventTarget;
    }

    public static void fireEvent(List<VideoItem> videos) {
        eventTarget.fireEvent(new GettingVideoEvent(videos));
    }
}
