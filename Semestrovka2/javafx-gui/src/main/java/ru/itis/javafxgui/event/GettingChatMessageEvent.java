package ru.itis.javafxgui.event;

import javafx.event.Event;
import javafx.event.EventType;

public class GettingChatMessageEvent extends Event {
    public static final EventType<GettingChatMessageEvent> TYPE =
            new EventType<>(Event.ANY, "GETTING_CHAT_MESSAGE_EVENT_TYPE");
    private final String messageText;

    public GettingChatMessageEvent(String messageText) {
        super(TYPE);
        this.messageText = messageText;
    }

    public String getMessageText() {
        return messageText;
    }
}
