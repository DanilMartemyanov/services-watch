package ru.itis.javafxgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.itis.javafxgui.back.ClientStarter;
import ru.itis.socketclient.client.SocketClient;
import ru.itis.socketclient.exception.ClientException;


import java.io.IOException;
import java.net.InetAddress;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            ClientStarter.start();
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("video-choose.fxml"));

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch();
    }
}