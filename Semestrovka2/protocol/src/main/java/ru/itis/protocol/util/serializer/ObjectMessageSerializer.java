package ru.itis.protocol.util.serializer;

import ru.itis.protocol.message.*;

import java.io.*;

// object <-> byte
public class ObjectMessageSerializer {

    public static byte[] serialize(Message message) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {

            objectOutputStream.writeObject(message);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Message deserialize(int messageType, byte[] bytes) { // ToDo: fix
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {

            return (Message) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException();
        }
    }
}
