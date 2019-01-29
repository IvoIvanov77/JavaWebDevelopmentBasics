package org.softuni.create_classes.http;
import org.softuni.create_classes.enums.HttpStatusCode;

import java.util.Map;

public interface HttpResponse {

    Map<String, String> getHeaders();

    HttpStatusCode getStatusCode();

    byte[] getContent();

    byte[] getBytes();

    void setStatusCode(HttpStatusCode statusCode);

    void setContent(byte[] content);

    void addHeader(String header, String value);


}
