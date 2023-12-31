package ru.itis.javafxgui.back.handler;

import ru.itis.javafxgui.event.source.PausingVideoEventSource;
import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.property.MessageTypes;
import ru.itis.socketclient.handler.AbstractClientMessageHandler;

public class PauseVideoMessageHandler extends AbstractClientMessageHandler {
    @Override
    public void handle(Message message) {
        PausingVideoEventSource.fireEvent();
    }

    @Override
    public int getType() {
        return MessageTypes.PAUSE_VIDEO;
    }
}
