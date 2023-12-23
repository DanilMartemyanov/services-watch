package ru.itis.socketclient;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class SimpleClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 8082);
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();

        boolean clientSendRequestByGetMedia = false;
        boolean clientReadMedia = false;
        int b;
        File file = new File("/home/bebra/Снимки экрана/fromClientSocket.mp4");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        while (true) {
            if (!clientSendRequestByGetMedia) {
                out.write(1);
                clientSendRequestByGetMedia = true;
                System.out.println("client send request by get media");
                Thread.sleep(1000);

            }
            b = in.read();
            System.out.println("client read byte");
            if (b==-1){
                fileOutputStream.flush();
                break;
            }
            if (b==1 && !clientReadMedia){
                clientReadMedia = true;
                System.out.println("client ready to read media");
                continue;
            }
            if (clientReadMedia) {
                System.out.println("client read media");
                fileOutputStream.write(b);
                fileOutputStream.flush();
            }
        }
    }
}
