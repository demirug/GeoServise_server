package ua.demirug.geoservice;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class User {

    String uuid;
    Date last_packet;
    Location location;

    private class Location {
        float x;
        float y;
    }
}
