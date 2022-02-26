package ua.demirug.geoservice.handlers;

import com.sun.net.httpserver.HttpExchange;
import ua.demirug.geoservice.User;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class RegistrationHandler {

    /* Generate and returned UUID */
    public void handle(HttpExchange exchange) throws IOException {

        if (!exchange.getRequestMethod().equals("POST")) {
            throw new UnsupportedOperationException();
        }

        String uuid = UUID.randomUUID().toString();
        User.builder().uuid(uuid).build();

        exchange.sendResponseHeaders(200, uuid.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(uuid.getBytes());
        output.flush();

        exchange.close();
    }
}
