package ru.itis.socketserver.server;

import ru.itis.protocol.message.Message;
import ru.itis.socketserver.handler.ServerMessageHandler;

import java.rmi.ServerException;

public interface Server {
    void registerHandler(final ServerMessageHandler listener) throws ServerException;
    void sendMessage(final int connectionId, final Message message) throws ServerException;
    void sendBroadCastMessage(final Message message) throws ServerException;
    void start() throws ServerException;
}
