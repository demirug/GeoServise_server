package ua.demirug.geoservice;

import com.sun.net.httpserver.HttpServer;
import ua.demirug.geoservice.handlers.RegistrationHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Application {

    public static void main(String[] args) throws IOException {
        int serverPort = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);

        RegistrationHandler registrationHandler = new RegistrationHandler();

        server.createContext("/register",  registrationHandler::handle);
        //server.createContext("/get",  registrationHandler::handle);
        //server.createContext("/add",  registrationHandler::handle);

        server.setExecutor(null);
        server.start();
    }

}
