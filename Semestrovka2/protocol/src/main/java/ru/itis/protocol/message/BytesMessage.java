package ru.itis.protocol.message;

public class BytesMessage extends Message{
    protected final byte[] data;
    protected static final int type = 0;

    public BytesMessage(byte[] data) {
        super(type);
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}
