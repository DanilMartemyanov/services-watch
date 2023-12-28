package ru.itis.socketserver.handler;

import ru.itis.protocol.message.ChatMessage;
import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.property.MessageTypes;

import java.rmi.ServerException;

public class ChatMessageServerHandler extends AbstractServerMessageHandler{
    @Override
    public void handle(int connectionId, Message message) {
        ChatMessage chatMessage = (ChatMessage) message;
        try {
            this.server.sendBroadCastMessage(chatMessage);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getType() {
        return MessageTypes.SEND_TEXT_IN_CHAT;
    }
}
