package ru.itis.protocol.message;

import java.io.Serializable;

public class StopVideoMessage extends Message implements Serializable {
    public final static int type = 11;

    public StopVideoMessage() {
        super(type);
    }
}
