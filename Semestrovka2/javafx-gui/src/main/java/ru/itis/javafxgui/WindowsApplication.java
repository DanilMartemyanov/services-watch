package ru.itis.javafxgui;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.itis.javafxgui.back.ClientStarter;
import ru.itis.socketclient.exception.ClientException;

public class WindowsApplication extends Application {
    @Override
    public void start(Stage stage) {
        ClientStarter starter = new ClientStarter();
        try {
            starter.start();
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}