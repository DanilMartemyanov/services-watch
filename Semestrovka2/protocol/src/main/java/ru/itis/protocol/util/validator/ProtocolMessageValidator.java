package ru.itis.protocol.util.validator;

import ru.itis.protocol.message.property.MessageProperty;
import ru.itis.protocol.message.property.MessageTypes;

import java.util.Arrays;

public class ProtocolMessageValidator {
    public static boolean isValidType(int type) {
        return type == MessageTypes.BYTES
                || type == MessageTypes.SEND_TEXT_IN_CHAT
                || type == MessageTypes.SEND_VIDEO
                || type == MessageTypes.REQUEST_BY_GET_VIDEO
                || type == MessageTypes.SEND_SUM
                || type == MessageTypes.CALCULATE_SUM
                || type == MessageTypes.VIDEO
                || type == MessageTypes.PAGE_VIDEO_GET_REQUEST
                || type == MessageTypes.PAGE_VIDEO_RESPONSE
                || type == MessageTypes.CHOOSE_VIDEO
                || type == MessageTypes.PAUSE_VIDEO
                || type == MessageTypes.SYNCHRONIZE;
    }

    public static boolean isValidLength(int length) {
        return length <= MessageProperty.MAX_LENGTH;
    }

    public static boolean isAvailableProtocolVersion(byte[] version) {
        return Arrays.equals(version, MessageProperty.START_BYTES);
    }
}
