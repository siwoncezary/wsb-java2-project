package pl.wsb.apps;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.*;

public class HttpClientDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException, TimeoutException {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create("http://api.openweathermap.org/data/2.5/weather?q=kielce&appid=615d3ad8681ea723781b4255adda0859"))
                .GET()
                .build();

        HttpClient client =
                HttpClient.newBuilder().build();
        CompletableFuture<HttpResponse<String>> resp = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .handleAsync((response, exception) -> {
                    if (exception != null)  {
                        System.out.println(exception);
                    }
                    System.out.println(response.body());
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        JsonNode jsonNode = mapper.readTree(response.body());
                        JsonNode coord = jsonNode.get("coord");
                        System.out.println(coord.get("lon").asDouble());
                        System.out.println(coord.get("lat").asDouble());
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return response;
                });
        Thread.sleep(1000);
    }
}
