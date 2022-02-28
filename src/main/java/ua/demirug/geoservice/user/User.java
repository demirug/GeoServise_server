package ua.demirug.geoservice.user;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Value
@Builder
public class User {

    UUID uuid;

    @Getter(AccessLevel.NONE)
    Date last_request;

    Location location;

    public String  getLast_request() {
        return this.last_request.toString();
    }

}