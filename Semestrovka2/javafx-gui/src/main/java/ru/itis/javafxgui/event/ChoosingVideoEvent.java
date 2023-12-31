package ru.itis.javafxgui.event;

import javafx.event.Event;
import javafx.event.EventType;
import ru.itis.protocol.item.VideoItem;

public class ChoosingVideoEvent extends Event {
    public static final EventType<ChoosingVideoEvent> TYPE =
            new EventType<>(Event.ANY, "CHOOSE_VIDEO_EVENT_TYPE");
    private final VideoItem video;

    public ChoosingVideoEvent(VideoItem video) {
        super(TYPE);
        this.video = video;
    }

    public VideoItem getVideo() {
        return video;
    }
}
