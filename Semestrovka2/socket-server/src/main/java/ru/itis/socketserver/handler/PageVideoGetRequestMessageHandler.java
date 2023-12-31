package ru.itis.socketserver.handler;

import ru.itis.protocol.item.VideoItem;
import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.PageVideoGetRequestMessage;
import ru.itis.protocol.message.PageVideoResponseMessage;
import ru.itis.protocol.message.VideoMessage;
import ru.itis.protocol.message.property.MessageTypes;
import ru.itis.socketserver.model.Video;
import ru.itis.socketserver.repository.VideoRepository;
import ru.itis.socketserver.repository.VideoRepositoryImpl;

import javax.sql.DataSource;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Обработка запроса на получения страницы видосов
public class PageVideoGetRequestMessageHandler extends AbstractServerMessageHandler {
    private final DataSource dataSource;

    public PageVideoGetRequestMessageHandler(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void handle(int connectionId, Message message) {
        PageVideoGetRequestMessage pageVideoGetRequestMessage = (PageVideoGetRequestMessage) message;
        VideoRepository videoRepository = new VideoRepositoryImpl(dataSource);
        int numberPage = pageVideoGetRequestMessage.getNumberPage();
        int pageSize = pageVideoGetRequestMessage.getPageSize();
        List<Video> videos = new ArrayList<>();
        try {
            videos = videoRepository.getPage(numberPage, pageSize);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<VideoItem> videoMessages = videos.stream()
                .map(video -> new VideoItem(
                        video.getUri(),
                        video.getName(),
                        video.getLikesAmount(),
                        video.getDislikesAmount()))
                .toList();
        PageVideoResponseMessage result = new PageVideoResponseMessage(numberPage, pageSize, videoMessages);

        try {
            server.sendMessage(connectionId, result);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getType() {
        return MessageTypes.PAGE_VIDEO_GET_REQUEST;
    }
}
