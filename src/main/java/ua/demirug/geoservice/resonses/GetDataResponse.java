package ua.demirug.geoservice.resonses;

import java.util.Map;

public record GetDataResponse(Map<String, Map<String, Object>> data) implements Response {}