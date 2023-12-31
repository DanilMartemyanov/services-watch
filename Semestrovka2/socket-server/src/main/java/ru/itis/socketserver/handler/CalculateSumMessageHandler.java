package ru.itis.socketserver.handler;

import ru.itis.protocol.message.CalculateSumIntNumbersMessage;
import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.SumNumbersMessage;
import ru.itis.protocol.message.property.MessageTypes;

import java.rmi.ServerException;

// useless
public class CalculateSumMessageHandler extends AbstractServerMessageHandler {
    @Override
    public void handle(final int connectionId, final Message message) {
        CalculateSumIntNumbersMessage calculateSumIntNumbersMessage = (CalculateSumIntNumbersMessage) message;
        int result = calculateSumIntNumbersMessage.getNumber1() + calculateSumIntNumbersMessage.getNumber2();
        SumNumbersMessage sumNumbersMessage = new SumNumbersMessage(result);
        try {
            this.server.sendBroadCastMessage(sumNumbersMessage);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getType() {
        return MessageTypes.CALCULATE_SUM;
    }
}
