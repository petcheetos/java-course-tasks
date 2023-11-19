package edu.project3.log;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogParser {
    private static final String SPLITTER = " ";
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss").localizedBy(
            Locale.ENGLISH);
    private static final Pattern PATTERN_ADDRESS = Pattern.compile(
        "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
    private static final Pattern PATTERN_USER = Pattern.compile("\\s-\\s[^\\[]+");
    private static final Pattern PATTERN_DATE_TIME = Pattern.compile("\\d{2}/[A-z]{3,}/\\d{4}(:\\d{2}){3}");
    private static final Pattern PATTERN_TYPE_URI_PROTOCOL = Pattern.compile("\"(\\w+)\\s+([^\\s]+)\\s+([^\"]+)\"");
    private static final Pattern PATTERN_STATUS_BYTES = Pattern.compile("\\d{3}\\s\\d+\\s");
    private static final Pattern PATTERN_USER_AGENT = Pattern.compile("\"([^\"]*)\" \"([^\"]*)\"");

    private LogParser() {
    }

    private static Log parseLog(String logEntry) {
        Matcher addressMatcher = PATTERN_ADDRESS.matcher(logEntry);
        Matcher userMatcher = PATTERN_USER.matcher(logEntry);
        Matcher dateTimeMatcher = PATTERN_DATE_TIME.matcher(logEntry);
        Matcher typeUriProtocolMatcher = PATTERN_TYPE_URI_PROTOCOL.matcher(logEntry);
        Matcher statusBytesMatcher = PATTERN_STATUS_BYTES.matcher(logEntry);
        Matcher userAgentMatcher = PATTERN_USER_AGENT.matcher(logEntry);

        if (!(addressMatcher.find() && userMatcher.find() && dateTimeMatcher.find() && typeUriProtocolMatcher.find()
            && statusBytesMatcher.find() && userAgentMatcher.find())) {
            return null;
        }
        String address = addressMatcher.group();
        String user = userMatcher.group();
        LocalDateTime time = LocalDateTime.parse(dateTimeMatcher.group(), DATE_TIME_FORMATTER);
        String[] typeUriProtocol = typeUriProtocolMatcher.group().split(SPLITTER);
        Log.RequestType requestType = Enum.valueOf(Log.RequestType.class, typeUriProtocol[0].substring(1));
        URI requestAddress = URI.create(typeUriProtocol[1]);
        String protocol = typeUriProtocol[2];

        String[] statusBytes = statusBytesMatcher.group().split(SPLITTER);
        int responseCode = Integer.parseInt(statusBytes[0]);
        int bytes = Integer.parseInt(statusBytes[1]);

        String[] userData = userAgentMatcher.group().split(SPLITTER);
        String httpReferer = userData[0];
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < userData.length; i++) {
            builder.append(userData[i]);
            if (i != userData.length - 1) {
                builder.append(SPLITTER);
            }
        }
        String userAgent = builder.toString();

        return new Log(address, user, time, requestType, requestAddress, protocol,
            responseCode, bytes, httpReferer, userAgent
        );
    }

    public static List<Log> getLogsList(String string) {
        List<String> list = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(string, "\n");
        while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
        }
        return list.stream().map(LogParser::parseLog).collect(Collectors.toList());
    }
}
