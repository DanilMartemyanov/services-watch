package ru.itis.javafxgui.back.handler;

import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.SumNumbersMessage;
import ru.itis.protocol.message.property.MessageTypes;
import ru.itis.socketclient.handler.AbstractClientMessageHandler;

// useless class for example
public class SumMessageHandler extends AbstractClientMessageHandler {
    @Override
    public void handle(Message message) {
        System.out.println("new message: sum = " + ((SumNumbersMessage) message).getNumber());
    }

    @Override
    public int getType() {
        return MessageTypes.SEND_SUM;
    }
}
