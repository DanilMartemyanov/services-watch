package ru.itis.socketclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import ru.itis.protocol.ProtocolMessageManager;
import ru.itis.protocol.message.ChatMessage;


public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 8082);
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();
        ChatMessage message = new ChatMessage("HELLO WORLD!!!");
        System.out.println(message.getType());
        byte[] bytes = ProtocolMessageManager.getBytes(message);
        out.write(bytes);
        System.out.println(bytes.length);
        out.flush();
    }
}
