package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private HackerNews() {
    }

    public static long[] hackerNewsTopStories() {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .build();
        try (HttpClient client = HttpClient.newBuilder()
            .build()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String[] responseStrings = response.body().substring(1, response.body().length() - 1).split(",");
            return Arrays.stream(responseStrings).mapToLong(Long::parseLong).toArray();
        } catch (IOException | InterruptedException e) {
            return new long[0];
        }
    }

    public static String news(long id) {
        URI uri = URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json");
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .build();
        try (HttpClient client = HttpClient.newBuilder()
            .build()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Pattern pattern = Pattern.compile("\"title\":\"([\\w|\s]+)\"");
            Matcher matcher = pattern.matcher(response.body());
            if (matcher.find()) {
                return matcher.group(1);
            }
            return "";
        } catch (IOException | InterruptedException e) {
            return "";
        }
    }
}
