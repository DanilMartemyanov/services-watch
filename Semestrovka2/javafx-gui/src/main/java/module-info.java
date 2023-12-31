module ru.itis.javafxgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires ru.itis.socketclient;
    requires ru.itis.protocol;
    requires javafx.web;


    requires com.almasb.fxgl.all;

    opens ru.itis.javafxgui to javafx.fxml;
    exports ru.itis.javafxgui;
    exports ru.itis.javafxgui.back;
    opens ru.itis.javafxgui.back to javafx.fxml;
    exports ru.itis.javafxgui.back.handler;
    opens ru.itis.javafxgui.back.handler to javafx.fxml;
    exports ru.itis.javafxgui.event;
    opens ru.itis.javafxgui.event to javafx.fxml;
    exports ru.itis.javafxgui.event.source;
    opens ru.itis.javafxgui.event.source to javafx.fxml;
}