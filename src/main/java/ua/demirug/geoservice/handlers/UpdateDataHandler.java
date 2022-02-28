package ua.demirug.geoservice.handlers;

import com.sun.net.httpserver.HttpExchange;
import ua.demirug.geoservice.resonses.MessageResponse;
import ua.demirug.geoservice.resonses.Response;
import ua.demirug.geoservice.services.UserManager;
import ua.demirug.geoservice.services.datebase.AccountBase;
import ua.demirug.geoservice.user.Location;
import ua.demirug.geoservice.user.User;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UpdateDataHandler extends Handler {

    @Override
    public Response handle(HttpExchange exchange) {

        Map<String, List<String>> args = this.splitQuery(exchange.getRequestURI().getRawQuery());
        if(args.containsKey("uuid") && args.containsKey("x") && args.containsKey("y") && args.containsKey("datetime")) {

            User user = UserManager.getUser(args.get("uuid").get(0));
            float set_x;
            float set_y;
            Date date;

            try {
                set_x = Float.parseFloat(args.get("x").get(0));
                set_y = Float.parseFloat(args.get("y").get(0));
                date = User.dateFormat.parse(args.get("datetime").get(0));
                if(user == null || set_x < 0 || set_y  < 0 || set_x > 360 || set_y > 360) {
                    return new MessageResponse("Incorrect input data");
                }
            } catch (NumberFormatException | ParseException e) {
                return new MessageResponse("Incorrect input data");
            }

            user.setLast_request(date);
            user.setLocation(Location.builder()
                    .x(set_x)
                    .y(set_y)
                    .build());

            AccountBase.getInstance().updateUser(user);

            return new MessageResponse("OK");

        } else {
            return new MessageResponse("Post must contains fields: 'uuid', 'datetime', 'x', 'y'");
        }
    }
}
