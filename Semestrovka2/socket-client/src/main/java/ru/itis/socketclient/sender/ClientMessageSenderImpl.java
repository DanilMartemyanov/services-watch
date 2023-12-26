package ru.itis.socketclient.sender;

import ru.itis.socketclient.client.Client;
import ru.itis.protocol.message.CalculateSumIntNumbersMessage;
import ru.itis.protocol.message.ChatMessage;
import ru.itis.socketclient.exception.ClientException;

public class ClientMessageSenderImpl implements ClientMessageSender {
    protected Client client;

    public ClientMessageSenderImpl(Client client) throws ClientException{
        this.client = client;
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
