package ru.itis.javafxgui.back.handler;

import ru.itis.javafxgui.event.source.GettingChatMessageEventSource;
import ru.itis.protocol.message.ChatMessage;
import ru.itis.protocol.message.Message;
import ru.itis.protocol.message.property.MessageTypes;
import ru.itis.socketclient.handler.AbstractClientMessageHandler;

public class ChatMessageClientHandler extends AbstractClientMessageHandler {
    @Override
    public void handle(Message message) {
        String messageText = ((ChatMessage) message).getText();
        GettingChatMessageEventSource.fireEvent(messageText);
    }

    @Override
    public int getType() {
        return MessageTypes.SEND_TEXT_IN_CHAT;
    }
}
