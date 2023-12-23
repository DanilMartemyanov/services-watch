package ru.itis.socketserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8082);
        Socket socket = server.accept();
        InputStream in = socket.getInputStream();
        int b;
        OutputStream out = socket.getOutputStream();
        byte[] media;
        while ((b = in.read()) != -1) {
            System.out.println("----");
            System.out.println("<< " + b);
            if (b==1){
                System.out.println("server was get request by export media");
                media = getMedia("/home/bebra/Снимки экрана/EXILE_STOPBAN_DILBLIN_MiMiMaMaMu_Клип_Бебра_2_Premium.mp4");
                out.write(1);
                out.write(media);
            } else {
                System.out.println("----");
                System.out.println("<< " + b);
            }
        }
    }

    public static byte[] getMedia(String path) throws IOException {
        File file = new File(path);
        InputStream reader  = new FileInputStream(file);
        List<Byte> bytes = new LinkedList<>();
        int r;
        while ((r = reader.read()) != -1) {
            System.out.println("server get list of bytes");
            bytes.add((byte) r);
        }
        byte[] result = new byte[bytes.size()];
        Iterator<Byte> iterator = bytes.iterator();
        int i = 0;
        while (iterator.hasNext()){
            System.out.println("server get array of bytes");
            result[i] = iterator.next();
            i++;
        }


        return result;
    }
}