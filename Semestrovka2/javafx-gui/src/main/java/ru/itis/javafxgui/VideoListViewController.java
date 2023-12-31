package ru.itis.javafxgui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ru.itis.javafxgui.back.ClientStarter;
import ru.itis.javafxgui.event.ChoosingVideoEvent;
import ru.itis.javafxgui.event.GettingVideoEvent;
import ru.itis.javafxgui.event.source.ChoosingVideoEventSource;
import ru.itis.javafxgui.event.source.GettingVideosEventSource;
import ru.itis.protocol.item.VideoItem;
import ru.itis.socketclient.exception.ClientException;

import java.io.IOException;


public class VideoListViewController {

    @FXML
    private ListView<VideoItem> videoListView; // VideoItem - модель видео из message

    @FXML
    public void initialize() {
        // Настройка CellFactory для ListView, CellFactory - интерфейс для настрйоки отображения в каждой ячейке
        videoListView.setCellFactory(listView -> new ListCell<VideoItem>() {
            private ImageView thumbnail = new ImageView();
            private Label title = new Label();

            @Override
            protected void updateItem(VideoItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    //getThumbnailUrl() возвращает URL миниатюры YouTube
                    thumbnail.setImage(new Image(item.getUri()));
                    thumbnail.setFitWidth(120);
                    thumbnail.setPreserveRatio(true);
                    title.setText(item.getName());
                    setGraphic(new HBox(10, thumbnail, title)); // 10 - отступ между миниатюрой и названием
                }
            }
        });
        // Настройка CellFactory для ListView
        // ----------------------------------------------------------------------
        // запрос на получение списка видео
        try {
            ClientStarter.getMessageSender().sendRequestByGetPageOfVideos(3, 5);
        } catch (ClientException e) {
            System.out.println("Oops, error");
        }
        // запрос на получение списка видео
        // ----------------------------------------------------------------------
        // описание действия, при получении списка видео
        Button addVideoInListController = new Button();
        GettingVideosEventSource gettingVideosEventSource = new GettingVideosEventSource(addVideoInListController);
        addVideoInListController.addEventHandler(GettingVideoEvent.TYPE, gettingVideoEvent -> {
            System.out.println("hiihihi");
            videoListView.getItems().addAll(gettingVideoEvent.getVideos());
        });
        // описание действия, при получении списка видео
        // ----------------------------------------------------------------------
        // описание действия для старта видео
        Button startVideoController = new Button();
        ChoosingVideoEventSource choosingVideoEventSource = new ChoosingVideoEventSource(startVideoController);
        startVideoController.addEventHandler(ChoosingVideoEvent.TYPE, choosingVideoEvent -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    HelloController.setVideoUri(choosingVideoEvent.getVideo().getUri());
                    switchToVideoScene();
                }
            });

        });
        // описание действия для старта видео
        // ----------------------------------------------------------------------
        // описание действия, при нажатии на видео
        videoListView.setOnMouseClicked(event -> {
            // Двойной клик и выбран элемент
            if (event.getClickCount() == 2 && !videoListView.getSelectionModel().isEmpty()) {
                VideoItem videoItem = videoListView.getSelectionModel().getSelectedItem(); // Выбранное видео
                try {
                    ClientStarter.getMessageSender().sendChooseVideoMessage(videoItem);
                } catch (ClientException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        // описание действия, при нажатии на видео
    }

    public void switchToVideoScene(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent videoRoot = null;
        try {
            videoRoot = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene videoScene = new Scene(videoRoot);
        Stage currentStage = (Stage) videoListView.getScene().getWindow(); // выбирается текущее окно
        currentStage.setScene(videoScene); // устанавливается новая сцена
    }
}
