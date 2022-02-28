package ua.demirug.geoservice.handlers;

import com.sun.net.httpserver.HttpExchange;
import ua.demirug.geoservice.resonses.MessageResponse;
import ua.demirug.geoservice.resonses.Response;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;

public abstract class Handler {

    private Jsonb jsonb = JsonbBuilder.create();

    public Map<String, List<String>> splitQuery(String query) {
        if (query == null || "".equals(query)) {
            return Collections.emptyMap();
        }

        return Pattern.compile("&").splitAsStream(query)
                .map(s -> Arrays.copyOf(s.split("="), 2))
                .collect(groupingBy(s -> decode(s[0]), mapping(s -> decode(s[1]), toList())));

    }

    private String decode(final String encoded) {
        return encoded == null ? null : URLDecoder.decode(encoded, StandardCharsets.UTF_8);
    }

    public void run_handle(HttpExchange exchange) throws IOException {

        if (!exchange.getRequestMethod().equals("POST")) {
            throw new UnsupportedOperationException();
        }

        System.out.println("POST " + exchange.getRequestURI());

        Response response = this.handle(exchange);
        response = response != null ? response : new MessageResponse("No response was given");

        String data = this.jsonb.toJson(response);

        exchange.sendResponseHeaders(200, data.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(data.getBytes());
        output.flush();

        exchange.close();
    }

    public abstract Response handle(HttpExchange exchange);

}
