package ru.itis.socketserver.handler;

import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.property.MessageTypes;

public class PageVideoGetRequestMessageHandler extends AbstractServerMessageHandler {
    @Override
    public void handle(int connectionId, Message message) {

    }

    @Override
    public int getType() {
        return MessageTypes.PAGE_VIDEO_GET_REQUEST;
    }
}
