package ru.itis.socketclient.sender;

import ru.itis.socketclient.exception.ClientException;

public interface ClientMessageSender {
    void sendChatMessage(String text) throws ClientException;

    void sendSumOf(int a, int b) throws ClientException;
}
