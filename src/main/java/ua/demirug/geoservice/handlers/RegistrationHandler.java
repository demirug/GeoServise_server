package ua.demirug.geoservice.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import ua.demirug.geoservice.services.UserManager;
import ua.demirug.geoservice.user.User;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class RegistrationHandler extends Handler {

    private ObjectMapper objectMapper = new ObjectMapper();

    /* Generate user and returned User JSON */
    public void handle(HttpExchange exchange) throws IOException {

        if (!exchange.getRequestMethod().equals("POST")) {
            throw new UnsupportedOperationException();
        }

        User user = UserManager.registerUser(UUID.randomUUID());

        String data = this.objectMapper.writeValueAsString(user);

        exchange.sendResponseHeaders(200, data.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(data.getBytes());
        output.flush();

        exchange.close();
    }
}
