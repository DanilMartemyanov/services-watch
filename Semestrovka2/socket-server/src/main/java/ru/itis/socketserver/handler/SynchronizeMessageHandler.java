package ru.itis.socketserver.handler;

import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.property.MessageTypes;

import java.rmi.ServerException;

public class SynchronizeMessageHandler extends AbstractServerMessageHandler{
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
        return MessageTypes.SYNCHRONIZE;
    }
}
