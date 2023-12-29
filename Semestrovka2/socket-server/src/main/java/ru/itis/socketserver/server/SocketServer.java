package ru.itis.socketserver.server;

import ru.itis.protocol.ProtocolMessageManager;
import ru.itis.protocol.message.Message;
import ru.itis.socketserver.handler.AbstractServerMessageHandler;
import ru.itis.socketserver.handler.ServerMessageHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

public class SocketServer implements Server {
    protected List<AbstractServerMessageHandler> handlers;
    protected int port;
    protected ServerSocket server;
    protected boolean started;
    // ToDo: to encapsulate to List<Connection>
    protected List<Socket> sockets;

    public SocketServer(int port) {
        this.handlers = new ArrayList<>();
        this.port = port;
        // ToDo: change data structure for keeping sockets (connections in future after encapsulated).
        //  with ArrayList may be trouble because something with indexing and offsets. (c) A.Ferenets!!!
        this.sockets = new ArrayList<>();
        this.started = false;
    }

    @Override
    public void registerHandler(final AbstractServerMessageHandler handler) throws ServerException {
        if (started) {
            throw new ServerException("Server has been started already.");
        }
        handler.init(this);
        this.handlers.add(handler);
    }

    @Override
    public void sendMessage(final int connectionId, final Message message) throws ServerException {
        try {
            Socket s = sockets.get(connectionId);
            s.getOutputStream().write(ProtocolMessageManager.getBytes(message));
        } catch (IOException e) {
            throw new ServerException("Problem with sendBroadCastMessage. " + e.getMessage());
        }
    }

    @Override
    public void sendBroadCastMessage(final Message message) throws ServerException {
        try {
            for (Socket socket : sockets) {
                socket.getOutputStream().write(ProtocolMessageManager.getBytes(message));
            }
        } catch (IOException e) {
            throw new ServerException("Problem with sendBroadCastMessage. " + e.getMessage());
        }
    }

    @Override
    public void start() throws ServerException {
        
        try {
            // Start server
            server = new ServerSocket(this.port);
            started = true;
            // Proccess connections
            Socket socket;
            while (true) {
                socket = server.accept();
                System.out.println("Подключился новый пользователь");
                handleConnection(socket);
            }
        } catch (IOException ex) {
            throw new ServerException("Problem with server starting.", ex);
        }
    }

    protected void handleConnection(Socket socket) throws ServerException {
        sockets.add(socket);
        int connectionId = sockets.lastIndexOf(socket);
        // ToDo: fix (too much required parameters)
        Thread thread = new Thread(new ConnectionListener(handlers, socket, connectionId));
        thread.start();
    }
}
