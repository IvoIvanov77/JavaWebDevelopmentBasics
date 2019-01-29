package org.softuni.parsing_http_requests;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import static org.softuni.parsing_http_requests.Constants.*;

public class HttpParser {

   private String getResponseLine(HttpStatus response) {
        return String.format(RESPONSE_LINE, response.getStatusCode(), response.getStatusPhrase());
    }

    public  void buildResponse(String method,
                                      Map<String, String> headers,
                                      Map<String, String> bodyParams,
                                      StringBuilder responseBuilder) {
        String username = decodeAuthorization(headers.get(HEADER_AUTHORIZATION));

        String responseBody;
        switch (HttpMethod.valueOf(method)) {
        case POST:
            responseBody = String.format(RESPONSE_BODY_POST, username,
                    bodyParams.get(PARAM_NAME), bodyParams.get(PARAM_QUANTITY), bodyParams.get(PARAM_PRICE));
            break;
        case GET:
            responseBody = String.format(RESPONSE_BODY_GET, username);
            break;
        default:
            throw new IllegalArgumentException("Unknown or unsupported HTTP method: " + method);

        }

        buildResponse(headers, responseBuilder,
                getResponseLine(HttpStatus.OK),
                responseBody);
    }

    private String decodeAuthorization(String encoded) {
        if (!encoded.startsWith(AUTHORIZATION_PREFIX)) {
            throw new IllegalArgumentException("Unknown encoding for string: " + encoded);
        }

        return new String(Base64.getDecoder()
                .decode(encoded.substring(AUTHORIZATION_PREFIX.length()).getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8);
    }

    public boolean isValidRequest(Set<String> urls,
                                          Map<String, String> request,
                                          Map<String, String> headers,
                                          Map<String, String> bodyParams,
                                          StringBuilder responseBuilder) {

        if (!urls.contains(request.get(REQUEST_RESOURCE))) {
            buildResponse(headers, responseBuilder,
                    getResponseLine(HttpStatus.NOT_FOUND),
                    HttpStatus.NOT_FOUND.getDescription());
        } else if (!headers.containsKey(HEADER_AUTHORIZATION)) {
            buildResponse(headers, responseBuilder,
                    getResponseLine(HttpStatus.UNAUTHORIZED),
                    HttpStatus.UNAUTHORIZED.getDescription());
        } else if (HttpMethod.POST == HttpMethod.valueOf(request.get(REQUEST_METHOD)) &&
                !bodyParams.keySet().containsAll(REQUIRED_BODY_PARAMS)) {
            buildResponse(headers, responseBuilder,
                    getResponseLine(HttpStatus.BAD_REQUEST),
                    HttpStatus.BAD_REQUEST.getDescription());
        } else {
            return true;
        }

        return false;
    }

    private void buildResponse(Map<String, String> headers,
                               StringBuilder responseBuilder,
                               String responseLine,
                               String responseBody) {
        responseBuilder.append(responseLine)
                .append(HTTP_LINE_SEPARATOR);
        attachResponseHeaders(headers, responseBuilder);
        responseBuilder.append(responseBody);
    }

    private void attachResponseHeaders(Map<String, String> headers, StringBuilder responseBuilder) {
        for (String responseHeader : RESPONSE_HEADERS) {
            if (HEADER_DATE.equals(responseHeader)) {
                responseBuilder.append(responseHeader)
                        .append(HEADER_SEPARATOR)
                        .append(LocalDate.now().format(DATE_FORMATTER))
                        .append(HTTP_LINE_SEPARATOR);
                continue;
            }
            String value = headers.get(responseHeader);
            if (value != null) {
                responseBuilder.append(responseHeader)
                        .append(HEADER_SEPARATOR)
                        .append(value)
                        .append(HTTP_LINE_SEPARATOR);
            }
        }
        responseBuilder.append(HTTP_LINE_SEPARATOR);
    }

    public Set<String> parseUrls(BufferedReader reader) throws IOException {
        Set<String> urls = new HashSet<>();

        String urlsLine = reader.readLine();
        Matcher matcher = URLS_PATTERN.matcher(urlsLine);
        while (matcher.find()) {
            urls.add(matcher.group());
        }

        return Collections.unmodifiableSet(urls);
    }

    public Map<String, String> parseRequestLine(BufferedReader reader) throws IOException {
        String requestLine = reader.readLine();

        Matcher matcher = REQUEST_LINE_PATTERN.matcher(requestLine);

        if (!matcher.matches() ||
                !HTTP_VERSIONS.contains(matcher.group(REQUEST_HTTP_VERSION)) ||
                !HTTP_METHODS.contains(matcher.group(REQUEST_METHOD))) {
            throw new IllegalArgumentException("Invalid Request Line: " + requestLine);
        }

        String httpVersion = matcher.group(REQUEST_HTTP_VERSION);
        String httpMethod = matcher.group(REQUEST_METHOD);
        String resource = matcher.group(REQUEST_RESOURCE);

        return Map.of(REQUEST_METHOD, httpMethod,
                REQUEST_RESOURCE, resource,
                REQUEST_HTTP_VERSION, httpVersion);
    }

    public Map<String, String> parseHeaders(BufferedReader reader) throws IOException {
        Map<String, String> headers = new HashMap<>();
        String header;
        while ((header = reader.readLine()) != null && header.length() > 0) {
            Matcher matcher = HEADER_PATTERN.matcher(header);
            if (matcher.matches() && VALID_HEADERS.contains(matcher.group(KEY))) {
                String key = matcher.group(KEY);
                String value = matcher.group(VALUE);
                headers.put(key, value);
            }
        }
        return Collections.unmodifiableMap(headers);
    }

    public Map<String, String> parseBodyParams(BufferedReader reader) throws IOException {
        Map<String, String> params = new HashMap<>();

        String paramsLine = reader.readLine();
        if (paramsLine != null) {
            Matcher matcher = BODY_PARAMS_PATTERN.matcher(paramsLine);
            while (matcher.find()) {
                String paramName = matcher.group(KEY);
                if (REQUIRED_BODY_PARAMS.contains(paramName)) {
                    String paramValue = matcher.group(VALUE);
                    params.put(paramName, paramValue);
                }
            }
        }

        return Collections.unmodifiableMap(params);
    }
}
