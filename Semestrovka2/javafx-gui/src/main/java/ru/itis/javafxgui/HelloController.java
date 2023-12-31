package ru.itis.javafxgui;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import ru.itis.javafxgui.back.ClientStarter;
import ru.itis.javafxgui.event.GettingChatMessageEvent;
import ru.itis.javafxgui.event.PausingVideoEvent;
import ru.itis.javafxgui.event.SynchronizingVideoEvent;
import ru.itis.javafxgui.event.source.GettingChatMessageEventSource;
import ru.itis.javafxgui.event.source.GettingVideosEventSource;
import ru.itis.javafxgui.event.source.PausingVideoEventSource;
import ru.itis.javafxgui.event.source.SynchronizingVideoEventSource;
import ru.itis.socketclient.exception.ClientException;

public class HelloController {
    @FXML
    public TextArea chatArea;
    @FXML
    public TextField inputField;
    @FXML
    private Label welcomeText;
    @FXML
    private WebView webView;
    @FXML
    private TextField textField;
    private static String videoUri;
    private WebEngine webEngine;
    private static boolean videoPlay = true;

    @FXML
    protected void onHelloButtonClick() {
        String message = inputField.getText();
        if (message.isEmpty()) {
            throw new IllegalArgumentException();
        }
        try {
            ClientStarter.getMessageSender().sendChatMessage(message);
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
        inputField.clear();
    }

    // ToDo: почему-то воспроизведение видео не начинается сразу после загрузки, поэтому нужно будет сделать кнопку
    //  synchronize, которая будет устанавливать одно и то же время в видео-плеере у всех пользователей.
    public void initialize() throws InterruptedException {
        webEngine = webView.getEngine();
        String videoId = parse(videoUri); // parse video id
        webEngine.load("https://www.youtube.com/embed/" + videoId); // video load
        // ----------------------------------------------------------------------
        // Тут происходит обработка воспроизведения и остановки видео.
        // Название PausingVideoEvent не самое удачное, так как оно происходит не только когда видео ставится на паузу,
        // но и когда пауза отжимается и воспроизведение продолжается
        Button stopVideoController = new Button();
        PausingVideoEventSource pausingVideoEventSource = new PausingVideoEventSource(stopVideoController);
        stopVideoController.addEventHandler(PausingVideoEvent.TYPE, pausingVideoEvent -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if (!videoPlay) {
                        playVideo();
                        videoPlay = true;
                    } else {
                        pauseVideo();
                        videoPlay = false;
                    }
                }
            });
        });
        // Тут происходит обработка воспроизведения и остановки видео.
        // ----------------------------------------------------------------------
        // Тут происходит синхронизация видео
        Button synchronizeVideoController = new Button();
        SynchronizingVideoEventSource synchronizeVideoControllerSource =
                new SynchronizingVideoEventSource(synchronizeVideoController);
        synchronizeVideoController.addEventHandler(SynchronizingVideoEvent.TYPE, synchronizingVideoEvent -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    scrollVideo(
                            synchronizingVideoEvent.getHour(),
                            synchronizingVideoEvent.getMinute(),
                            synchronizingVideoEvent.getSecond()
                    );
                }
            });
        });
        // Тут происходит синхронизация видео
        // ----------------------------------------------------------------------
        // Прием сообщений в чате
        Button chatMessageController = new Button();
        GettingChatMessageEventSource gettingChatMessageEventSource
                = new GettingChatMessageEventSource(chatMessageController);
        chatMessageController.addEventHandler(GettingChatMessageEvent.TYPE, gettingChatMessageEvent -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    String message = gettingChatMessageEvent.getMessageText();
                    if (!message.isEmpty()) {
                        chatArea.appendText("Anonymous: " + message + "\n");
                    }
                }
            });
        });
        // Прием сообщений в чате
    }

    @FXML
    protected void onPauseClick(ActionEvent actionEvent) {
        try {
            ClientStarter.getMessageSender().sendPauseVideoMessage();
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onLikeButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onDislikeButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onSynchronizeClick(ActionEvent actionEvent) {
        try {
            Double k = getCurrentTimeInPlayer();
            System.out.println(k);
            ClientStarter.getMessageSender().sendSynchronizeMessage(0, 0, k.intValue());
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setVideoUri(String videoUri) {
        HelloController.videoUri = videoUri;
    }

    private void pauseVideo(){
        String script =
                "var video = document.querySelector('video');" +
                        "if(video) {" +
                        "video.pause();" +
                        "} else {" +
                        "console.log('Video element not found');" +
                        "}";
        webEngine.executeScript(script);
    }

    private void playVideo(){
        String script =
                "var video = document.querySelector('video');" +
                        "if(video) {" +
                        "video.play();" +
                        "} else {" +
                        "console.log('Video element not found');" +
                        "}";
        webEngine.executeScript(script);
    }

    private void scrollVideo(int hour, int minute, int second){
        int totalSecond = 60*60*hour + 60*minute + second;
        String script =
                "var video = document.querySelector('video');" +
                        "if(video) {" +
                        "video.currentTime = " + totalSecond + ";" +
                        "} else {" +
                        "console.log('Video element not found');" +
                        "}";
        webEngine.executeScript(script);
    }

    private String parse(String videoUri) {
        char[] chars = videoUri.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 32; i < 43; i++) {
            builder.append(chars[i]);
        }

        return builder.toString();
    }
    
    private double getCurrentTimeInPlayer(){
        String script = "var videoElement = document.querySelector('video');" +
                "if (videoElement) {" +
                "    videoElement.currentTime;" +
                "} else {" +
                "    'Video element not found';" +
                "}";
        Object result = webEngine.executeScript(script);

        if (result != null) {
            return (double) result;
        } else {
            System.out.println("Ошибка при получении текущего времени воспроизведения");
        }
        return 0;
    }
}

// получить текущее время, отправить запрос на синхронизацию, синхронизироваться