package ru.itis.javafxgui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private WebView webView;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void initialize() {
        WebEngine webEngine = webView.getEngine();
        String videoId ="jirQpN2wr9A";
        String content = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<iframe width=\"1268\" height=\"570\" src=\"https://www.youtube.com/embed/" + videoId + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"
                + "</body>"
                + "</html>";
        webEngine.loadContent(content);
    }
}