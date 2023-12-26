package ru.itis.socketclient.handler;

import ru.itis.protocol.message.Message;
import ru.itis.socketclient.client.Client;

public interface ClientMessageHandler {
    void init(Client client);

    void handle(Message message);

    int getType();
}
