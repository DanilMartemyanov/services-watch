package ru.itis.protocol.message;

import java.io.Serializable;
import java.util.UUID;

// useless
public class VideoMessage extends Message implements Serializable {
    private String uri;
    private String name;
    private int likesAmount;
    private int dislikesAmount;
    public static final int type = 6;

    public VideoMessage(String uri, String name, int likesAmount, int dislikesAmount) {
        super(type);
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
