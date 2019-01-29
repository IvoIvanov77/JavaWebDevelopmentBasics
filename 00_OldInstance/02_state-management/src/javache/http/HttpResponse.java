package javache.http;

import javache.enums.HttpStatusCode;

import java.util.Map;

public interface HttpResponse {

    Map<String, String> getHeaders();

    HttpStatusCode getStatusCode();

    byte[] getContent();

    byte[] getBytes();

    void setStatusCode(HttpStatusCode statusCode);

    void setContent(byte[] content);

    void addHeader(String header, String value);

    void setCookie(HttpCookie cookie);
}
