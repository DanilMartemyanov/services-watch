package ru.itis.socketserver.server;

import ru.itis.protocol.ProtocolMessageManager;
import ru.itis.protocol.message.Message;
import ru.itis.socketserver.handler.AbstractServerMessageHandler;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ConnectionListener implements Runnable {
    private final int connectionId;
    private final Socket socket;
    private final List<AbstractServerMessageHandler> handlers;

    public ConnectionListener(List<AbstractServerMessageHandler> handlers, Socket socket, int connectionId) {
        this.handlers = handlers;
        this.socket = socket;
        this.connectionId = connectionId;
    }

    @Override
    public void run() {
        Message message = null;
        while (true) {
            try {
                message = ProtocolMessageManager.readMessage(socket.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (AbstractServerMessageHandler handler : handlers) {
                if (message.getType() == handler.getType()) {
                    System.out.println("Обработка сообщения");
                    handler.handle(connectionId, message);
                }
            }
        }
    }
}
