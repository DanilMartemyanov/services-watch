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
    @Override
    public void handle(int connectionId, Message message) {
        try {
            server.sendBroadCastMessage(message);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getType() {
        return MessageTypes.CHOOSE_VIDEO;
    }
}
