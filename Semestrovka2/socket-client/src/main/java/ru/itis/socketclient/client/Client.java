package ru.itis.socketclient.client;

import ru.itis.protocol.message.Message;
import ru.itis.socketclient.exception.ClientException;
import ru.itis.socketclient.handler.AbstractClientMessageHandler;

public interface Client {
    void registerHandler(final AbstractClientMessageHandler handler) throws ClientException;

    void sendMessage(final Message message) throws ClientException;

    void start() throws ClientException;
}
