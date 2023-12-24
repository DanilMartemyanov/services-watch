package ru.itis.protocol.message;

public class SumNumbersMessage extends Message {
    protected int number;
    public static final int type = 4;

    public SumNumbersMessage(int number) {
        super(type);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
