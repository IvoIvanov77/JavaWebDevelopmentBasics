package org.softuni.create_classes.http;



import org.softuni.create_classes.enums.HttpMethod;

import java.util.Map;

public interface HttpRequest {

    Map<String, String> getHeaders();

    Map<String, String> getBodyParametars();

    HttpMethod getMethod();

    String getRequestUrl();

    boolean isResource();

    void setMethod(HttpMethod method);

    void setRequestUrl(String url);

    void addHeader(String headerName, String headerValue);

    void addBodyParameter(String key, String value);






}
