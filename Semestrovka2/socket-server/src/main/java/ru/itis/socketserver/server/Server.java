package ru.itis.socketserver.server;

import ru.itis.protocol.message.Message;
import ru.itis.socketserver.handler.AbstractServerMessageHandler;

import java.rmi.ServerException;

public interface Server {
    void registerHandler(final AbstractServerMessageHandler handler) throws ServerException;

    void sendMessage(final int connectionId, final Message message) throws ServerException;

    void sendBroadCastMessage(final Message message) throws ServerException;

    void start() throws ServerException;
}
