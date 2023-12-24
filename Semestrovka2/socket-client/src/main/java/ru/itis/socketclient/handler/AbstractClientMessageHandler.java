package ru.itis.socketclient.handler;

import ru.itis.socketclient.client.Client;

import java.util.List;

public abstract class AbstractClientMessageHandler<T> implements ClientMessageHandler{
    protected List<? super T> resource;
    protected Client client;

    public AbstractClientMessageHandler(List<? super T> resource){
        this.resource = resource;
    }

    @Override
    public void init(Client client) {
        this.client = client;
    }
}
