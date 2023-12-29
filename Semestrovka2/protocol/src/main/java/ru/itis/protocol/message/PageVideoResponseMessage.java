package ru.itis.protocol.message;

import ru.itis.protocol.item.VideoItem;

import java.io.Serializable;
import java.util.List;

// ответ на запрос на получение страницы видосов
public class PageVideoResponseMessage extends Message implements Serializable {
    protected final int pageNumber;
    protected final int pageSize;
    protected final List<VideoItem> videos;
    public final static int type = 8;

    public PageVideoResponseMessage(int pageNumber, int pageSize, List<VideoItem> videos) {
        super(type);
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.videos = videos;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<VideoItem> getVideos() {
        return videos;
    }
}
