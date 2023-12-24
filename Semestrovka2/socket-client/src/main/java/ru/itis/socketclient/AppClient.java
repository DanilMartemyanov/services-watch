package ru.itis.socketclient;

import ru.itis.socketclient.client.Client;
import ru.itis.socketclient.client.SocketClient;
import ru.itis.socketclient.exception.ClientException;
import ru.itis.socketclient.handler.ClientSumMessageHandlerImpl;
import ru.itis.socketclient.sender.ClientMessageSender;
import ru.itis.socketclient.sender.ClientMessageSenderImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class AppClient {
    public static void main(String[] args) throws UnknownHostException, ClientException, InterruptedException {
        Client client = new SocketClient(InetAddress.getLocalHost(), 8082);
        List<Integer> list = new ArrayList<>();
        client.registerHandler(new ClientSumMessageHandlerImpl(list));
        client.start();
        ClientMessageSender clientMessageSender = new ClientMessageSenderImpl(client);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i =0;
                boolean flag = false;
                while (true){
                    try {
                        // до конца не знаю с чем это связано, но если не делать sleep, то каким-то образом
                        // list.size() будет равен +1, и i тоже будет равен +1 но sout не вывидеться (37-40) 🤡🤡🤡🤡🤡
                        // мб это проблема конкретно System.out.println, но хз...
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (list.size()>i){
                        System.out.println("from AppClient: " + list.get(i));
                        i++;
                    }
                }
            }
        }).start();
        clientMessageSender.sendSumOf(4,6);
    }
}
