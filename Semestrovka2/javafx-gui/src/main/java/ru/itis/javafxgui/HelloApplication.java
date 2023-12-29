package ru.itis.javafxgui;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.itis.javafxgui.back.ClientStarter;
import ru.itis.socketclient.client.SocketClient;
import ru.itis.socketclient.exception.ClientException;


import java.io.IOException;
import java.net.InetAddress;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        ClientStarter starter = new ClientStarter();
        try {
            starter.start();
            starter.getMessageSender().sendStopVideoMessage();
            Thread.sleep(1000);
            starter.getMessageSender().sendChooseVideoMessage("https://www.youtube.com/watch?v=KhX3T_NYndo&list=PLaxxU3ZabospOFUVjRWofD-mYOQfCxpzw");
        } catch (ClientException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}