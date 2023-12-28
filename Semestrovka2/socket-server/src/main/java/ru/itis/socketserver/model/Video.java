package ru.itis.socketserver.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class Video {
    private UUID uuid;
    private String uri;
    private String name;
    private int likesAmount;
    private int dislikesAmount;
}
