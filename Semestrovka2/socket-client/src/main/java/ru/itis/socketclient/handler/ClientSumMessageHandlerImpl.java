package ru.itis.socketclient.handler;

import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.SumNumbersMessage;
import ru.itis.socketclient.client.Client;

import java.util.List;

public class ClientSumMessageHandlerImpl extends AbstractClientMessageHandler<Integer> {
    public ClientSumMessageHandlerImpl(List<? super Integer> resource) {
        super(resource);
    }

    @Override
    public void handle(Message message) {
        System.out.println("new message: sum = " + ((SumNumbersMessage) message).getNumber());
        resource.add(((SumNumbersMessage) message).getNumber());
    }

    @Override
    public int getType() {
        return 4;
    }
}
