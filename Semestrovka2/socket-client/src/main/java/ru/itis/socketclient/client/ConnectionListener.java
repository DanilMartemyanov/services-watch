package ru.itis.socketclient.client;

import ru.itis.protocol.ProtocolMessageManager;
import ru.itis.protocol.message.Message;
import ru.itis.socketclient.handler.ClientMessageHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ConnectionListener implements Runnable {
    private final InputStream io;
    private final List<ClientMessageHandler> handlers;

    ConnectionListener(InputStream io, List<ClientMessageHandler> handlers) {
        this.io = io;
        this.handlers = handlers;
    }

    @Override
    public void run() {
        try {
            Message message;
            while (true) {
                message = ProtocolMessageManager.readMessage(io);
                for (ClientMessageHandler handler : handlers) {
                    if (handler.getType() == message.getType()) {
                        handler.handle(message);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
