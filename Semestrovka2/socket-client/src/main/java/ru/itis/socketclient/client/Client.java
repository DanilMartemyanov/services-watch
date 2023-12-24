package ru.itis.socketclient.client;

import ru.itis.protocol.message.Message;
import ru.itis.socketclient.exception.ClientException;
import ru.itis.socketclient.handler.ClientMessageHandler;

public interface Client {
    void registerHandler(final ClientMessageHandler handler) throws ClientException;
    void sendMessage(final Message message) throws ClientException;
    void start() throws ClientException;
}
