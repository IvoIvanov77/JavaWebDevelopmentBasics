package org.softuni.parsing_http_requests;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Constants {

    public static final Logger LOGGER = Logger.getLogger(HttpParser.class.getName());

    public static final String HTTP_LINE_SEPARATOR = "\r\n";
    public static final String HEADER_SEPARATOR = ": ";
    public static final String REQUEST_METHOD = "method";
    public static final String REQUEST_RESOURCE = "resource";
    public static final String REQUEST_HTTP_VERSION = "version";
    public static final String PARAM_NAME = "name";
    public static final String PARAM_QUANTITY = "quantity";
    public static final String PARAM_PRICE = "price";
    public static final String HEADER_DATE = "Date";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_PREFIX = "Basic ";
    public static final String HTTP_1_1 = "HTTP/1.1";
    public static final String KEY = "key";
    public static final String VALUE = "value";

    public static final String RESPONSE_LINE = HTTP_1_1 + " %d %s";
    public static final String RESPONSE_BODY_GET = "Greetings %s!";
    public static final String RESPONSE_BODY_POST = RESPONSE_BODY_GET +
            " You have successfully created %s with quantity – %s, price – %s.";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static final Pattern REQUEST_LINE_PATTERN = Pattern.compile(String.format(
            "^(?<%s>[A-Z]{3,6}) (?<%s>/[a-zA-Z0-9/]+) (?<%s>HTTP/[0-9.]+)$",
            REQUEST_METHOD, REQUEST_RESOURCE, REQUEST_HTTP_VERSION));

    public static final Pattern BODY_PARAMS_PATTERN = Pattern.compile(String.format(
            "&?(?<%s>[A-Za-z0-9]+)=(?<%s>[A-Za-z0-9]+)",
            KEY, VALUE));

    public static final Pattern HEADER_PATTERN = Pattern.compile(String.format(
            "^(?<%s>[^ :]+)%s(?<%s>.+)$",
            KEY, HEADER_SEPARATOR, VALUE));

    public static final Pattern URLS_PATTERN = Pattern.compile("(/[^ ]+)");

    public static final List<String> RESPONSE_HEADERS = List.of(HEADER_DATE, "Host", "Content-Type");
    public static final Set<String> VALID_HEADERS = Set.of(HEADER_DATE, "Host", "Content-Type", HEADER_AUTHORIZATION);
    public static final Set<String> REQUIRED_BODY_PARAMS = Set.of(PARAM_NAME, PARAM_QUANTITY, PARAM_PRICE);
    public static final Set<String> HTTP_VERSIONS = Set.of(HTTP_1_1);
    public static final Set<String> HTTP_METHODS = Stream.of(HttpMethod.values())
            .map(Enum::name)
            .collect(Collectors.toUnmodifiableSet());

    private Constants(){

    }
}
