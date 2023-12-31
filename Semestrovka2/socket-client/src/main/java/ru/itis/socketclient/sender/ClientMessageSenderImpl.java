package ru.itis.socketclient.sender;

import ru.itis.protocol.item.VideoItem;
import ru.itis.protocol.message.*;
import ru.itis.socketclient.client.Client;
import ru.itis.socketclient.exception.ClientException;

public class ClientMessageSenderImpl implements ClientMessageSender {
    protected Client client;

    public ClientMessageSenderImpl(Client client) throws ClientException{
        this.client = client;
    }

    @Override
    public void sendChatMessage(String text) throws ClientException {
        ChatMessage message = new ChatMessage(text);
        client.sendMessage(message);
    }

    @Override
    public void sendSumOf(int a, int b) throws ClientException {
        CalculateSumIntNumbersMessage calculateSumIntNumbersMessage = new CalculateSumIntNumbersMessage(a, b);
        client.sendMessage(calculateSumIntNumbersMessage);
    }

    @Override
    public void sendRequestByGetPageOfVideos(int pageNumber, int pageSize) throws ClientException {
        PageVideoGetRequestMessage pageVideoGetRequestMessage = new PageVideoGetRequestMessage(pageNumber, pageSize);
        client.sendMessage(pageVideoGetRequestMessage);
    }

    @Override
    public void sendChooseVideoMessage(VideoItem video) throws ClientException {
        ChooseVideoMessage chooseVideoMessage = new ChooseVideoMessage(video);
        client.sendMessage(chooseVideoMessage);
    }

    @Override
    public void sendPauseVideoMessage() throws ClientException {
        client.sendMessage(new StopVideoMessage());
    }

    @Override
    public void sendSynchronizeMessage(int hour, int minute, int second) throws ClientException {
        client.sendMessage(new SynchronizeMessage(hour, minute, second));
    }
}
