package ru.itis.socketserver.server;

import ru.itis.protocol.ProtocolMessageManager;
import ru.itis.protocol.message.Message;
import ru.itis.socketserver.handler.ServerMessageHandler;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ConnectionListener implements Runnable {
    int connectionId;
    Socket s;
    List<ServerMessageHandler> handlers;
    List<Socket> sockets;

    public ConnectionListener(List<ServerMessageHandler> handlers, Socket s, int connectionId) {
        this.handlers = handlers;
        this.s = s;
        this.connectionId = connectionId;
    }

    @Override
    public void run() {
        Message message = null;
        while (true){
            try {
                message = ProtocolMessageManager.readMessage(s.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("New message:");
            for(ServerMessageHandler handler : handlers){
                if(message.getType() == handler.getType()){
                    // One by one! Another left listeners will wait current
                    // Another thread could be created here or before for every Listener
                    handler.handle(connectionId, message);
                }
            }
        }
    }
}
