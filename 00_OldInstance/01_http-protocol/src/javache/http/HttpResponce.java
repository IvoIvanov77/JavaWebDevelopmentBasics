package javache.http;

import javache.enums.HttpStatus;

import java.util.Map;

public interface HttpResponce {

    Map<String, String> getHeaders();

    HttpStatus getStatusCode();

    byte[] getContent();

    byte[] getBytes();

    void setStatusCode(HttpStatus statusCode);

    void setContent(byte[] content);

    void addHeader(String header, String value);
}
