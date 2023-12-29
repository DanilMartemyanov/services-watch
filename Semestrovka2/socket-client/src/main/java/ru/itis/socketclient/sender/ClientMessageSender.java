package ru.itis.socketclient.sender;

import ru.itis.socketclient.exception.ClientException;

public interface ClientMessageSender {
    void sendChatMessage(String recipient, String text) throws ClientException;

    void sendSumOf(int a, int b) throws ClientException;

    void sendRequestByGetPageOfVideos(int pageNumber, int pageSize) throws ClientException;

    void sendChooseVideoMessage(String uri) throws ClientException;

    void sendStopVideoMessage() throws ClientException;
}
