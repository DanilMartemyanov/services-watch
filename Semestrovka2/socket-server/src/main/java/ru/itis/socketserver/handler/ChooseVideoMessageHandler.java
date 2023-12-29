package ru.itis.socketserver.handler;

import ru.itis.protocol.message.ChooseVideoMessage;
import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.VideoMessage;
import ru.itis.protocol.message.property.MessageTypes;
import ru.itis.socketserver.model.Video;
import ru.itis.socketserver.repository.VideoRepository;
import ru.itis.socketserver.repository.VideoRepositoryImpl;

import javax.sql.DataSource;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.Optional;

public class ChooseVideoMessageHandler extends AbstractServerMessageHandler {

    private final DataSource dataSource;

    public ChooseVideoMessageHandler(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void handle(int connectionId, Message message) {
        VideoRepository videoRepository = new VideoRepositoryImpl(dataSource);
        ChooseVideoMessage chooseVideoMessage = (ChooseVideoMessage) message;
        Optional<Video> videoOptional;
        try {
            videoOptional = videoRepository.findByUri(chooseVideoMessage.getUri());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (videoOptional.isPresent()) {
            Video video = videoOptional.get();
            VideoMessage videoMessage = new VideoMessage(
                    video.getUri(),
                    video.getName(),
                    video.getLikesAmount(),
                    video.getDislikesAmount()
            );
            try {
                server.sendBroadCastMessage(videoMessage);
            } catch (ServerException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int getType() {
        return MessageTypes.CHOOSE_VIDEO;
    }
}
