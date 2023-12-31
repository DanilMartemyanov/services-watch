package ru.itis.javafxgui.event;

import javafx.event.Event;
import javafx.event.EventType;
import ru.itis.protocol.item.VideoItem;

import java.util.List;

public class GettingVideoEvent extends Event {
    public static final EventType<GettingVideoEvent> TYPE =
            new EventType<>(Event.ANY, "GETTING_VIDEO_EVENT_TYPE");
    private final List<VideoItem> videos;

    public GettingVideoEvent(List<VideoItem> videos) {
        super(TYPE);
        this.videos = videos;
    }

    public List<VideoItem> getVideos() {
        return videos;
    }
}
