package ru.itis.socketclient.sender;

import ru.itis.protocol.message.CalculateSumIntNumbersMessage;
import ru.itis.protocol.message.ChatMessage;
import ru.itis.socketclient.client.Client;
import ru.itis.socketclient.exception.ClientException;

public class ClientMessageSenderImpl extends AbstractClientMessageSender {
    public ClientMessageSenderImpl(Client client) {
        super(client);
    }

    @Override
    public void sendChatMessage(String recipient, String text) throws ClientException {
        ChatMessage message = new ChatMessage(text);
        client.sendMessage(message);
    }

    @Override
    public void sendSumOf(int a, int b) throws ClientException {
        CalculateSumIntNumbersMessage calculateSumIntNumbersMessage = new CalculateSumIntNumbersMessage(a, b);
        client.sendMessage(calculateSumIntNumbersMessage);
    }
}
