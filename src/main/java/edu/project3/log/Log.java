package edu.project3.log;

import java.net.URI;
import java.time.LocalDateTime;

public record Log(
    String address,
    String user,
    LocalDateTime time,
    RequestType requestType,
    URI requestAddress,
    String protocol,
    int responseCode,
    int bytes,
    String httpReferer,
    String userAgent
) {
    public enum RequestType {
        GET,
        POST,
        PUT,
        DELETE,
        HEAD,
        PATCH,
        OPTIONS,
        TRACE,
        CONNECT
    }
}
