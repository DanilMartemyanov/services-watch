package ru.itis.protocol.message;

import java.io.Serializable;

// useless
public class CalculateSumIntNumbersMessage extends Message implements Serializable {
    protected int number1;
    protected int number2;
    public static final int type = 5;

    public CalculateSumIntNumbersMessage(int number1, int number2) {
        super(type);
        this.number1 = number1;
        this.number2 = number2;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }
}