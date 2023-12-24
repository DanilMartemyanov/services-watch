package ru.itis.socketserver;

import ru.itis.socketserver.handler.CalculateSumMessageHandler;
import ru.itis.socketserver.server.Server;
import ru.itis.socketserver.server.SocketServer;

public class AppServer {
    private static final int PORT = 8082;

    public static void main(String[] args) {
        try{
            Server server = new SocketServer(PORT);
            server.registerHandler(new CalculateSumMessageHandler());
            server.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
