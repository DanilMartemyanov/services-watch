package ru.itis.protocol;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.property.MessageProperty;
import ru.itis.protocol.util.serializer.ObjectMessageSerializer;
import ru.itis.protocol.util.validator.ProtocolMessageValidator;

// protocol message <-> message
public class ProtocolMessageManager {
    // object -> protocol message (byte[]):
    // 2 byte (info app version) + 4 byte (int messageType) + 4 byte (int messageLength) + n byte (serialization object)
    public static byte[] getBytes(final Message message) throws IllegalArgumentException, NullPointerException {
        if (message==null) {
            throw new NullPointerException("Message is null");
        }
        if (!ProtocolMessageValidator.isValidType(message.getType())) {
            throw new IllegalArgumentException("Invalid message type");
        }
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] serializationMessage = ObjectMessageSerializer.serialize(message);
            byte[] type = ByteBuffer.allocate(4)
                    .putInt(message.getType())
                    .array();
            byte[] messageLength = ByteBuffer.allocate(4)
                    .putInt(serializationMessage.length)
                    .array();
            byteArrayOutputStream.write(MessageProperty.START_BYTES);
            byteArrayOutputStream.write(type);
            byteArrayOutputStream.write(messageLength);
            byteArrayOutputStream.write(serializationMessage);

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Message readMessage(final InputStream inputStream) throws IOException, IllegalArgumentException {
        byte[] startBytes = new byte[MessageProperty.START_BYTES.length];
        byte[] messageTypeBytes = new byte[4];
        byte[] messageLengthBytes = new byte[4];
        int messageType;
        int messageLength;
        InputStream in = inputStream;
        in.read(startBytes, 0, MessageProperty.START_BYTES.length);
        if (!ProtocolMessageValidator.isAvailableProtocolVersion(startBytes)){
            throw new IllegalArgumentException("We can't proccess this message, please change version of protocol");
        }
        in.read(messageTypeBytes, 0, 4);
        messageType = ByteBuffer.wrap(messageTypeBytes,0,4).getInt();
        if (!ProtocolMessageValidator.isValidType(messageType)) {
            throw new IllegalArgumentException("Invalid message type");
        }
        in.read(messageLengthBytes, 0, 4);
        messageLength = ByteBuffer.wrap(messageLengthBytes, 0, 4).getInt();
        if (!ProtocolMessageValidator.isValidLength(messageLength)) {
            throw new IllegalArgumentException("Message can't be more " + MessageProperty.MAX_LENGTH);
        }
        byte[] messagePayload = new byte[messageLength];
        in.read(messagePayload,0,messageLength);

        return ObjectMessageSerializer.deserialize(messageType, messagePayload);
    }
}
