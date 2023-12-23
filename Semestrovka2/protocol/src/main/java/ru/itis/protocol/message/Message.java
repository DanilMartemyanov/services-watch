package ru.itis.protocol.message;

import java.io.Serializable;

public abstract class Message implements Serializable {
    protected final int type;

    public Message(int type) {
        this.type = type;
    }

    public final int getType() {
        return type;
    }
}
