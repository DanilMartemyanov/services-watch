package ru.itis.javafxgui.back;

import ru.itis.javafxgui.back.handler.PageVideoResponseMessageHandler;
import ru.itis.javafxgui.back.handler.StopVideoMessageHandler;
import ru.itis.javafxgui.back.handler.SumMessageHandler;
import ru.itis.javafxgui.back.handler.VideoMessageHandler;
import ru.itis.socketclient.client.Client;
import ru.itis.socketclient.client.SocketClient;
import ru.itis.socketclient.exception.ClientException;
import ru.itis.socketclient.sender.ClientMessageSender;
import ru.itis.socketclient.sender.ClientMessageSenderImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientStarter {
    private Client client;

    public void start() throws ClientException {
        Client client = null;
        try {
            client = new SocketClient(InetAddress.getLocalHost(), 8082);
        } catch (UnknownHostException e) {
            throw new ClientException(e);
        }
        // handler registration begin
        client.registerHandler(new SumMessageHandler());
        client.registerHandler(new VideoMessageHandler());
        client.registerHandler(new PageVideoResponseMessageHandler());
        client.registerHandler(new StopVideoMessageHandler());
        // handler registration end
        client.start();
        this.client = client;
    }

    public ClientMessageSender getMessageSender() throws ClientException {
        try {
            return new ClientMessageSenderImpl(client);
        } catch (ClientException e) {
            throw new ClientException(e);
        }
    }
}
