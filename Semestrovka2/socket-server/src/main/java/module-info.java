module ru.itis.socketserver {
    requires ru.itis.protocol;
    requires java.rmi;
    requires com.zaxxer.hikari;
    requires java.sql;
    requires static lombok;
    exports ru.itis.socketserver;
}
