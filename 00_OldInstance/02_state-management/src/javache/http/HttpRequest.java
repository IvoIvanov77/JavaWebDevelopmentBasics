package javache.http;

import javache.enums.HttpMethod;

import java.util.Map;

public interface HttpRequest {

    HttpMethod getMethod();

    String getRequestUrl();

    Map<String, String> getRequestHeaders();

    Map<String, String> getRequestBody();

    Map<String, HttpCookie> getCookies();

    boolean isResource();

}
