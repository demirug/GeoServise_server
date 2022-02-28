package ua.demirug.geoservice;

import com.sun.net.httpserver.HttpServer;
import ua.demirug.geoservice.handlers.RegistrationHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Application {

    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress(8000);
        HttpServer server = HttpServer.create(address, 0);

        server.createContext("/register",  new RegistrationHandler()::handle);

        server.setExecutor(null);
        server.start();

        System.out.println("Server started at " + address.getHostString() + ":" + address.getPort());
    }

}
