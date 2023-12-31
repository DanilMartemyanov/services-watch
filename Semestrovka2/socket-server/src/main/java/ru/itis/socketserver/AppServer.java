package ru.itis.socketserver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import ru.itis.socketserver.handler.CalculateSumMessageHandler;
import ru.itis.socketserver.handler.ChooseVideoMessageHandler;
import ru.itis.socketserver.handler.PageVideoGetRequestMessageHandler;
import ru.itis.socketserver.handler.StopVideoMessageHandler;
import ru.itis.socketserver.handler.ChatMessageServerHandler;

import ru.itis.socketserver.server.Server;
import ru.itis.socketserver.server.SocketServer;

import javax.sql.DataSource;
import java.util.Properties;

public class AppServer {
    private static final int PORT = 8082;
 
    public static void main(String[] args) {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        HikariConfig config = new HikariConfig(getDataBaseProperties());
        DataSource dataSource = new HikariDataSource(config);
        try{
            Server server = new SocketServer(PORT);
            server.registerHandler(new CalculateSumMessageHandler());
            server.registerHandler(new ChooseVideoMessageHandler(dataSource));
            server.registerHandler(new PageVideoGetRequestMessageHandler(dataSource));
            server.registerHandler(new StopVideoMessageHandler());
            server.registerHandler(new ChatMessageServerHandler());
            server.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ToDo: it's cringe. change
    private static Properties getDataBaseProperties(){
        Properties properties = new Properties();
        properties.setProperty("jdbcUrl", "jdbc:postgresql://localhost:5432/semestrovka");
        properties.setProperty("username", "postgres");
        properties.setProperty("password", "pKMFr7CW");

        return properties;
    }
}
