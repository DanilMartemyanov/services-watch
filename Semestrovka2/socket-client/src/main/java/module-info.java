module ru.itis.socketclient {
    requires ru.itis.protocol;

    exports ru.itis.socketclient.client;
    exports ru.itis.socketclient.sender;
    exports ru.itis.socketclient.handler;
    exports ru.itis.socketclient.exception;
}