package ua.demirug.geoservice.user;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class User {

   public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy HH:mm:ss z");

    UUID uuid;

    @Getter(AccessLevel.NONE)
    Date last_request;

    Location location;

    public String  getLast_request() {
        return dateFormat.format(this.last_request);
    }

}