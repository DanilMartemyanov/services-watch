package ru.itis.socketserver;

import ru.itis.protocol.ProtocolMessageManager;
import ru.itis.protocol.message.ChatMessage;
import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.property.MessageTypes;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(8082);

        int b=1;
        while (b!=0) {
            b=0;
            Socket socket = server.accept();
            Message message = ProtocolMessageManager.readMessage(socket.getInputStream());
            int messageType = message.getType();
            if (messageType == MessageTypes.BYTES) {
                System.out.println("bytes");
            }
            if (messageType == MessageTypes.SEND_TEXT_IN_CHAT) {
                System.out.println("It's ChatMessage!! Payload: " + ((ChatMessage) message).getText());
            }
            if (messageType == MessageTypes.REQUEST_BY_GET_VIDEO) {
                System.out.println(123);
            }
            if (messageType == MessageTypes.SEND_VIDEO) {
                System.out.println(321);
            }
        }
    }
}