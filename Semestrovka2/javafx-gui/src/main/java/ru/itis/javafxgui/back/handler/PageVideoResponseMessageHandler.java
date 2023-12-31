package ru.itis.javafxgui.back.handler;

import ru.itis.javafxgui.event.GettingVideosEventSource;
import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.PageVideoResponseMessage;
import ru.itis.protocol.message.property.MessageTypes;
import ru.itis.socketclient.handler.AbstractClientMessageHandler;

public class PageVideoResponseMessageHandler extends AbstractClientMessageHandler {
    @Override
    public void handle(Message message) {
        PageVideoResponseMessage pageVideoResponseMessage = (PageVideoResponseMessage) message;
        GettingVideosEventSource.fireEvent(pageVideoResponseMessage.getVideos());

        pageVideoResponseMessage.getVideos().forEach(l-> System.out.println(l.getName()));
    }

    @Override
    public int getType() {
        return MessageTypes.PAGE_VIDEO_RESPONSE;
    }
}
