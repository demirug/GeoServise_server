package ua.demirug.geoservice;

import com.sun.net.httpserver.HttpServer;
import ua.demirug.geoservice.config.ConfigProvider;
import ua.demirug.geoservice.handlers.GetDataHandler;
import ua.demirug.geoservice.handlers.UpdateDataHandler;
import ua.demirug.geoservice.handlers.RegistrationHandler;
import ua.demirug.geoservice.services.datebase.AccountBase;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Application {

    public static void main(String[] args) throws IOException {
        ConfigProvider.getInstance().load();
        AccountBase.getInstance().init();
        InetAddress host = InetAddress.getByName(ConfigProvider.getInstance().getString("host"));
        int port = ConfigProvider.getInstance().getInteger("port");

        InetSocketAddress address = new InetSocketAddress(host, port);
        HttpServer server = HttpServer.create(address, 0);

        server.createContext("/register",  new RegistrationHandler()::run_handle);
        server.createContext("/update",  new UpdateDataHandler()::run_handle);
        server.createContext("/get",  new GetDataHandler()::run_handle);

        server.setExecutor(null);
        server.start();

        Log.info("Server started at " + address.getHostString() + ":" + address.getPort());

    }

}
