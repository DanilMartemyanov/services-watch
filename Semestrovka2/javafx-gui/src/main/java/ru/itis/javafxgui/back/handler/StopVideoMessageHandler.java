package ru.itis.javafxgui.back.handler;

import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.property.MessageTypes;
import ru.itis.socketclient.handler.AbstractClientMessageHandler;

public class StopVideoMessageHandler extends AbstractClientMessageHandler {
    @Override
    public void handle(Message message) {
        System.out.println("STOP VIDEO!");
    }

    @Override
    public int getType() {
        return MessageTypes.STOP_VIDEO;
    }
}
