package ru.itis.javafxgui.back.handler;

import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.VideoMessage;
import ru.itis.protocol.message.property.MessageTypes;
import ru.itis.socketclient.handler.AbstractClientMessageHandler;

// useless class
public class VideoMessageHandler extends AbstractClientMessageHandler {
    @Override
    public void handle(Message message) {
        System.out.println(((VideoMessage) message).getName());
    }

    @Override
    public int getType() {
        return MessageTypes.VIDEO;
    }
}
