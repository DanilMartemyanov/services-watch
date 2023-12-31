package ru.itis.javafxgui.back.handler;

import ru.itis.javafxgui.event.source.SynchronizingVideoEventSource;
import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.SynchronizeMessage;
import ru.itis.protocol.message.property.MessageTypes;
import ru.itis.socketclient.handler.AbstractClientMessageHandler;

public class SynchronizeMessageHandler extends AbstractClientMessageHandler {
    @Override
    public void handle(Message message) {
        SynchronizeMessage synchronizeMessage = (SynchronizeMessage) message;
        SynchronizingVideoEventSource.fireEvent(
                synchronizeMessage.getHour(),
                synchronizeMessage.getMinute(),
                synchronizeMessage.getSecond()
        );
    }

    @Override
    public int getType() {
        return MessageTypes.SYNCHRONIZE;
    }
}
