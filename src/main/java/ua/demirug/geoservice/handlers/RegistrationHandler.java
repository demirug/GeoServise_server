package ua.demirug.geoservice.handlers;

import com.sun.net.httpserver.HttpExchange;

import ua.demirug.geoservice.Log;
import ua.demirug.geoservice.resonses.RegisterResponse;
import ua.demirug.geoservice.resonses.Response;
import ua.demirug.geoservice.services.UserManager;
import ua.demirug.geoservice.user.User;

import java.util.UUID;

public class RegistrationHandler extends Handler {


    /* Generate user and returned User JSON */

    @Override
    public Response handle(HttpExchange exchange) {

        User user = UserManager.registerUser(UUID.randomUUID());
        Log.warn("Registered user " + user.getUuid());
        return new RegisterResponse(user.getUuid().toString());
    }

}
