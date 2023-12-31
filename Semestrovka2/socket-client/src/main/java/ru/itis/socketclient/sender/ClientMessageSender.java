package ru.itis.socketclient.sender;

import ru.itis.protocol.item.VideoItem;
import ru.itis.socketclient.exception.ClientException;

public interface ClientMessageSender {
    void sendChatMessage(String text) throws ClientException;

    void sendSumOf(int a, int b) throws ClientException;

    void sendRequestByGetPageOfVideos(int pageNumber, int pageSize) throws ClientException;

    void sendChooseVideoMessage(VideoItem video) throws ClientException;

    void sendPauseVideoMessage() throws ClientException;

    void sendSynchronizeMessage(int hour, int minute, int second) throws ClientException;
}
