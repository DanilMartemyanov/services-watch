package ru.itis.protocol.message;

import java.io.Serializable;

// ToDo
public class PingPongMessage extends Message implements Serializable {
    public final static int type = 10;
    private byte pingOrPong;

    public PingPongMessage(byte pingOrPong) {
        super(type);
        this.pingOrPong = pingOrPong;
    }

    public byte getPingOrPong() {
        return pingOrPong;
    }
}
