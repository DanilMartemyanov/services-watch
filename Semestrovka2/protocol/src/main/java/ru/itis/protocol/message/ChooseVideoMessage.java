package ru.itis.protocol.message;

import java.io.Serializable;

public class ChooseVideoMessage extends Message implements Serializable {
    private String uri;
    public final static int type = 9;

    public ChooseVideoMessage(String uri) {
        super(type);
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
