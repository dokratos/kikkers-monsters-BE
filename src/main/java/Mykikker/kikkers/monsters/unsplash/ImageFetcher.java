package Mykikker.kikkers.monsters.unsplash;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ImageFetcher {
    @Value("${sources.unsplash-access}")
    private String unsplashKey;

    HttpClient client = HttpClient.newBuilder().build();

    public String[] fetchImage(String query, int numberOfCards) {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://api.unsplash.com/search/photos?per_page=" + numberOfCards + "&query=" + query + "&client_id=" + unsplashKey))
                .build();

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        try {
            HttpResponse<String> stringResponse = response.get();
            String body = stringResponse.body();
            ObjectMapper mapper = new ObjectMapper();
            RootDto unsplashResponse = mapper.readValue(body, RootDto.class);
            String[] imageList = unsplashResponse.getResult()
                    .stream()
                    .map(result -> result.getUrls().getSmall())
                    .toArray(String[]::new);
            return imageList;
        } catch (ExecutionException | InterruptedException | JsonProcessingException ex) {

        }
        return null;
    }
}
