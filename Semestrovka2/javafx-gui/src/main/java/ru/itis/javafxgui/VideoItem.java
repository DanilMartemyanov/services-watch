package ru.itis.javafxgui;

public class VideoItem {
    private String title;
    private String thumbnailUrl;

    public VideoItem(String title, String thumbnailUrl) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
