package ru.itis.socketclient.handler;

import ru.itis.socketclient.client.Client;

public abstract class AbstractClientMessageHandler implements ClientMessageHandler{
    protected Client client;

    @Override
    public void init(Client client) {
        this.client = client;
    }
}
