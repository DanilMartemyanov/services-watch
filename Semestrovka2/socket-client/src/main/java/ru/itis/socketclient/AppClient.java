package ru.itis.socketclient;

import ru.itis.socketclient.client.Client;
import ru.itis.socketclient.client.SocketClient;
import ru.itis.socketclient.exception.ClientException;
import ru.itis.socketclient.sender.ClientMessageSender;
import ru.itis.socketclient.sender.ClientMessageSenderImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class AppClient {
    public static void main(String[] args) throws UnknownHostException, ClientException, InterruptedException {
        Scanner scannerUsername = new Scanner(System.in);
        Scanner scannerText = new Scanner(System.in);

        Client client = new SocketClient(InetAddress.getLocalHost(), 8082);
        client.start();
        ClientMessageSender clientMessageSender = new ClientMessageSenderImpl(client);
        System.out.println("Введите имя:");
        String username = scannerUsername.nextLine();
        while(true){
            System.out.println("Введите текст сообщения");
            String textUser = scannerText.nextLine();
            clientMessageSender.sendChatMessage(username + ": " + textUser);

        }


    }
}
