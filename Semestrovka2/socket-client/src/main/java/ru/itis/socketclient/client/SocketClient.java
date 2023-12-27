package ru.itis.socketclient.client;

import ru.itis.protocol.ProtocolMessageManager;
import ru.itis.protocol.message.Message;
import ru.itis.socketclient.exception.ClientException;
import ru.itis.socketclient.handler.AbstractClientMessageHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketClient implements Client {

    protected Socket socket;
    private boolean isStarted;
    private final InetAddress inetAddress;
    private final int port;
    private final List<AbstractClientMessageHandler> handlers;

    public SocketClient(InetAddress inetAddress, int port){
        this.inetAddress = inetAddress;
        this.port = port;
        this.handlers = new ArrayList<>();
        isStarted = false;
    }

    @Override
    public void registerHandler(AbstractClientMessageHandler handler) throws ClientException {
        if (isStarted){
            throw new ClientException("Server is started");
        }
        handler.init(this);
        handlers.add(handler);
    }

    @Override
    public void sendMessage(Message message) throws ClientException {
        if (!isStarted){
            throw new ClientException("Server is started");
        }
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OutputStream out = socket.getOutputStream();
                        out.write(ProtocolMessageManager.getBytes(message));
                        System.out.println("message was send");
                        out.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }catch (RuntimeException e){
            throw new ClientException(e);
        }
    }

    @Override
    public void start() throws ClientException {
        if (isStarted){
            throw new ClientException("Server is started");
        }
        try{
            socket = new Socket(inetAddress, port);
            isStarted = true;
            // Listener will have take list of handlers that will be initialization and will have [this] (client)
            ConnectionListener socketClientListener = new ConnectionListener(socket.getInputStream(), handlers);
            Thread listener = new Thread(socketClientListener);
            listener.start();
        }
        catch(IOException ex){
            throw new ClientException("Problem with server starting", ex);
        }
    }

}
