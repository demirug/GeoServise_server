package ua.demirug.geoservice.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Location {
    float x;
    float y;
}