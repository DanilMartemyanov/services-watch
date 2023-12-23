package ru.itis.protocol.message;

import java.io.Serializable;

public class ChatMessage extends Message implements Serializable{
    protected String text;
    public static final int type = 3;

    public ChatMessage(String text) {
        super(type);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
