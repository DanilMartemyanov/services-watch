package ru.itis.javafxgui.back.handler;

import ru.itis.javafxgui.event.source.ChoosingVideoEventSource;
import ru.itis.protocol.message.ChooseVideoMessage;
import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.property.MessageTypes;
import ru.itis.socketclient.handler.AbstractClientMessageHandler;

public class ChooseVideoMessageHandler extends AbstractClientMessageHandler {
    @Override
    public void handle(Message message) {
        ChooseVideoMessage chooseVideoMessage = (ChooseVideoMessage) message;
        ChoosingVideoEventSource.fireEvent(chooseVideoMessage.getVideo());
    }

    @Override
    public int getType() {
        return MessageTypes.CHOOSE_VIDEO;
    }
}
