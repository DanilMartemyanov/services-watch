package ru.itis.socketclient;

import ru.itis.socketclient.client.Client;
import ru.itis.socketclient.client.SocketClient;
import ru.itis.socketclient.exception.ClientException;
import ru.itis.socketclient.sender.ClientMessageSender;
import ru.itis.socketclient.sender.ClientMessageSenderImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AppClient {
    public static void main(String[] args) throws UnknownHostException, ClientException, InterruptedException {
        Client client = new SocketClient(InetAddress.getLocalHost(), 8082);
        client.start();
        ClientMessageSender clientMessageSender = new ClientMessageSenderImpl(client);

        clientMessageSender.sendSumOf(4,6);
    }
}
