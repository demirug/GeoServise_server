package ua.demirug.geoservice.handlers;

import com.sun.net.httpserver.HttpExchange;
import ua.demirug.geoservice.resonses.GetDataResponse;
import ua.demirug.geoservice.resonses.MessageResponse;
import ua.demirug.geoservice.resonses.Response;
import ua.demirug.geoservice.services.UserManager;
import ua.demirug.geoservice.user.User;

import java.util.*;

public class GetDataHandler extends Handler {


    @Override
    public Response handle(HttpExchange exchange) {
        Map<String, List<String>> args = this.splitQuery(exchange.getRequestURI().getRawQuery());
        if(!args.containsKey("data")) {
            return new MessageResponse("No data was given");
        }
        List<String> uuids = args.get("data");

        Map<String, Map<String, Object>> data = new HashMap<>();

        uuids.forEach(uuid -> {
            User user = UserManager.getUser(uuid);
            if(user != null) {
               data.put(uuid, new HashMap<>() {{
                   this.put("x", user.getLocation().getX());
                   this.put("y", user.getLocation().getY());
                   this.put("datetime", user.getLast_request());
               }});
            }
        });

        return new GetDataResponse(data);
    }
}
