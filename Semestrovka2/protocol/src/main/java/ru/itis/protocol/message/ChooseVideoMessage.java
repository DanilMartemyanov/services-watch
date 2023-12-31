package ru.itis.protocol.message;

import ru.itis.protocol.item.VideoItem;

import java.io.Serializable;

public class ChooseVideoMessage extends Message implements Serializable {
    private final VideoItem video;
    public final static int type = 9;

    public ChooseVideoMessage(VideoItem video) {
        super(type);
        this.video = video;
    }

    public VideoItem getVideo() {
        return video;
    }
}
