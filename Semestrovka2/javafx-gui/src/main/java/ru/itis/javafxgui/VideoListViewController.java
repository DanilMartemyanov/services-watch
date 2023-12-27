package ru.itis.javafxgui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;


public class VideoListViewController {

    @FXML
    private ListView<VideoItem> videoListView; //VideoItem - модель видео из message

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
                    thumbnail.setImage(new Image(item.getThumbnailUrl()));
                    thumbnail.setFitWidth(120);
                    thumbnail.setPreserveRatio(true);
                    title.setText(item.getTitle());
                    setGraphic(new HBox(10, thumbnail, title)); // 10 - отступ между миниатюрой и названием
                }
            }
        });
        videoListView.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2 && !videoListView.getSelectionModel().isEmpty()) { // Двойной клик и выбран элемент
                try {
                    switchToVideoScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        loadVideos();
    }

    private void loadVideos() {
        videoListView.getItems().add(new VideoItem("Видео 1", "https://www.youtube.com/watch?v=jirQpN2wr9A/0.jpg"));
        videoListView.getItems().add(new VideoItem("Видео 2", "https://www.youtube.com/watch?v=jirQpN2wr9A/0.jpg"));

    }
    public void switchToVideoScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent videoRoot = loader.load();
        Scene videoScene = new Scene(videoRoot);

        Stage currentStage = (Stage) videoListView.getScene().getWindow();
        currentStage.setScene(videoScene);
    }
}
