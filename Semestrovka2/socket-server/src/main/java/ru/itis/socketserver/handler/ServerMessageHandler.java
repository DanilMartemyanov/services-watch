package ru.itis.socketserver.handler;

import ru.itis.protocol.message.Message;
import ru.itis.socketserver.server.Server;

public interface ServerMessageHandler {
    public void init(final Server server);

    public void handle(final int connectionId, final Message message);

    public int getType();
}
