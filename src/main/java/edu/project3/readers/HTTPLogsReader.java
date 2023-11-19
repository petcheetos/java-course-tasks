package edu.project3.readers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPLogsReader implements LogsReader {

    @Override
    public String read(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .build();
        try (HttpClient client = HttpClient.newBuilder().build()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }
}
