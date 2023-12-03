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
    private final DateTimeFormatter dateTimeFormatter =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss").localizedBy(Locale.ENGLISH);
    private final Pattern patternAddress = Pattern.compile(
        "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
    private final Pattern patternUser = Pattern.compile("\\s-\\s[^\\[]+");
    private final Pattern patternDateTime = Pattern.compile("\\d{2}/[A-z]{3,}/\\d{4}(:\\d{2}){3}");
    private final Pattern patternTypeUriProtocol = Pattern.compile("\"(\\w+)\\s+([^\\s]+)\\s+([^\"]+)\"");
    private final Pattern patternStatusBytes = Pattern.compile("\\d{3}\\s\\d+\\s");
    private final Pattern patternUserAgent = Pattern.compile("\"([^\"]*)\" \"([^\"]*)\"");

    public LogParser() {
    }

    private Log parseLog(String logEntry) {
        Matcher addressMatcher = patternAddress.matcher(logEntry);
        Matcher userMatcher = patternUser.matcher(logEntry);
        Matcher dateTimeMatcher = patternDateTime.matcher(logEntry);
        Matcher typeUriProtocolMatcher = patternTypeUriProtocol.matcher(logEntry);
        Matcher statusBytesMatcher = patternStatusBytes.matcher(logEntry);
        Matcher userAgentMatcher = patternUserAgent.matcher(logEntry);

        if (!(addressMatcher.find() && userMatcher.find() && dateTimeMatcher.find() && typeUriProtocolMatcher.find()
            && statusBytesMatcher.find() && userAgentMatcher.find())) {
            return null;
        }
        String address = addressMatcher.group();
        String user = userMatcher.group();
        LocalDateTime time = LocalDateTime.parse(dateTimeMatcher.group(), dateTimeFormatter);
        String splitter = " ";
        String[] typeUriProtocol = typeUriProtocolMatcher.group().split(splitter);
        Log.RequestType requestType = Enum.valueOf(Log.RequestType.class, typeUriProtocol[0].substring(1));
        URI requestAddress = URI.create(typeUriProtocol[1]);
        String protocol = typeUriProtocol[2];

        String[] statusBytes = statusBytesMatcher.group().split(splitter);
        int responseCode = Integer.parseInt(statusBytes[0]);
        int bytes = Integer.parseInt(statusBytes[1]);

        String[] userData = userAgentMatcher.group().split(splitter);
        String httpReferer = userData[0];
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < userData.length; i++) {
            builder.append(userData[i]);
            if (i != userData.length - 1) {
                builder.append(splitter);
            }
        }
        String userAgent = builder.toString();

        return new Log(address, user, time, requestType, requestAddress, protocol,
            responseCode, bytes, httpReferer, userAgent
        );
    }

    public List<Log> getLogsList(String string) {
        List<String> list = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(string, "\n");
        while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
        }
        LogParser parser = new LogParser();
        return list.stream().map(parser::parseLog).collect(Collectors.toList());
    }
}
