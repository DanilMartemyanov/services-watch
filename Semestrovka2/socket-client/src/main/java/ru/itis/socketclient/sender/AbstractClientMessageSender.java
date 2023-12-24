package ru.itis.socketclient.sender;

import ru.itis.socketclient.client.Client;

public abstract class AbstractClientMessageSender implements ClientMessageSender{
    protected Client client;

    public AbstractClientMessageSender(Client client){
        this.client = client;
    }
}
