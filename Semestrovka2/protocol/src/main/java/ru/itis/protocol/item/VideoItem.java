package ru.itis.protocol.item;

import java.io.Serializable;

public class VideoItem implements Serializable {
    private final String uri;
    private final String name;
    private final int likesAmount;
    private final int dislikesAmount;

    public VideoItem(String uri, String name, int likesAmount, int dislikesAmount) {
        this.uri = uri;
        this.name = name;
        this.likesAmount = likesAmount;
        this.dislikesAmount = dislikesAmount;
    }

    public String getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public int getLikesAmount() {
        return likesAmount;
    }

    public int getDislikesAmount() {
        return dislikesAmount;
    }
}
