package ru.itis.protocol.message;

import java.io.Serializable;

public class SynchronizeMessage extends Message implements Serializable {
    public static final int type = 13;
    private final int hour;
    private final int minute;
    private final int second;

    public SynchronizeMessage(int hour, int minute, int second) {
        super(type);
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
}
