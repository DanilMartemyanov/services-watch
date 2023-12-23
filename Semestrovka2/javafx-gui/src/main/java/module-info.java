module ru.itis.javafxgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens ru.itis.javafxgui to javafx.fxml;
    exports ru.itis.javafxgui;
}