package ru.itis.protocol.util.validator;

import ru.itis.protocol.message.property.MessageProperty;
import ru.itis.protocol.message.property.MessageTypes;

import java.util.Arrays;

public class ProtocolMessageValidator {
    public static boolean isValidType(int type) {
        return type == MessageTypes.BYTES
                || type == MessageTypes.SEND_TEXT_IN_CHAT
                || type == MessageTypes.SEND_VIDEO
                || type == MessageTypes.REQUEST_BY_GET_VIDEO;
    }

    public static boolean isValidLength(int length) {
        return length <= MessageProperty.MAX_LENGTH;
    }

    public static boolean isAvailableProtocolVersion(byte[] version) {
        return Arrays.equals(version, MessageProperty.START_BYTES);
    }
}
