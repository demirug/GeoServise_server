package ua.demirug.geoservice.user;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Value
@Builder
public class User {

    UUID uuid;

    Date last_request;

    Location location;


}