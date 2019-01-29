package org.softuni.parsing_http_requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import static org.softuni.parsing_http_requests.Constants.LOGGER;
import static org.softuni.parsing_http_requests.Constants.REQUEST_METHOD;

public class Main {
    public static void main(String[] args) {
        HttpParser parser = new HttpParser();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {

            Set<String> urls = parser.parseUrls(reader);
            Map<String, String> request = parser.parseRequestLine(reader);
            Map<String, String> headers = parser.parseHeaders(reader);
            Map<String, String> bodyParams = parser.parseBodyParams(reader);

            StringBuilder responseBuilder = new StringBuilder();
            if (parser.isValidRequest(urls, request, headers, bodyParams, responseBuilder)) {
                parser.buildResponse(request.get(REQUEST_METHOD), headers, bodyParams, responseBuilder);
            }

            System.out.println(responseBuilder.toString());
        } catch (IOException | IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, "ERROR", e);
        }
    }
}
