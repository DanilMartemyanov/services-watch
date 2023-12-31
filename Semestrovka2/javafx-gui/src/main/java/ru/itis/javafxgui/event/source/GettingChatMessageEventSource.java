package ru.itis.javafxgui.event.source;

import javafx.scene.control.Button;
import ru.itis.javafxgui.event.GettingChatMessageEvent;

public class GettingChatMessageEventSource {
    private static Button eventTarget;

    public GettingChatMessageEventSource(Button eventTarget) {
        GettingChatMessageEventSource.eventTarget = eventTarget;
    }

    public static void fireEvent(String messageText) {
        eventTarget.fireEvent(new GettingChatMessageEvent(messageText));
    }
}
