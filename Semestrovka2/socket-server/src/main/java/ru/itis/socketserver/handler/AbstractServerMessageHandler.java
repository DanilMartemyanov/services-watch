package ru.itis.socketserver.handler;

import ru.itis.socketserver.server.Server;

public abstract class AbstractServerMessageHandler implements ServerMessageHandler {
    protected boolean init;
    protected Server server;

    @Override
    public void init(final Server server) {
        this.server = server;
        this.init = true;
    }
}
