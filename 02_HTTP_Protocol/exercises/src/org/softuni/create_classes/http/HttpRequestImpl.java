package org.softuni.create_classes.http;

import org.softuni.create_classes.constans.HTTPConstants;
import org.softuni.create_classes.enums.HttpMethod;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestImpl implements HttpRequest {

    private String requestUrl;

    private HttpMethod httpMethod;

    private Map<String, String> requestHeaders;

    private Map<String, String> requestBody;

    public HttpRequestImpl(String requestContent) {
        this.initMethod(requestContent);
        this.initRequestUrl(requestContent);
        this.initHeaders(requestContent);
        this.initBody(requestContent);
    }

    private void initMethod(String requestContent) {
        this.httpMethod = HttpMethod.valueOf(requestContent.split(HTTPConstants.EMPTY_SPACE)[0].toUpperCase());
    }

    private void initRequestUrl(String requestContent) {
        this.requestUrl = requestContent.split(HTTPConstants.EMPTY_SPACE)[1];
    }

    private void initHeaders(String requestContent) {
        this.requestHeaders = new HashMap<>();
        String[] requestLines = requestContent.split(HTTPConstants.LINE_SEPARATOR);

        for (int i = 1; i < requestLines.length; i++){
            String requestLine = requestLines[i];

            if (requestLine.isEmpty()){
                break;
            }

            String[] keyValue = requestLine.split(HTTPConstants.COLON_SEPARATOR);
            this.requestHeaders.put(keyValue[0], keyValue[1]);
        }
    }


    private void initBody(String requestContent) {
        if(!this.httpMethod.equals(HttpMethod.POST)){
            return;
        }
        this.requestBody = new HashMap<>();
        boolean bodyBegin = false;

        String[] requestLines = requestContent.split(HTTPConstants.LINE_SEPARATOR);

        for (String requestLine : requestLines) {
            if(bodyBegin){
                String[] bodyParams = requestLine.split(HTTPConstants.AMPERSAND_SEPARATOR);
                for (String param : bodyParams) {
                    String[] keyValue = param.split(HTTPConstants.BODY_PARAM_KEY_VALUE_SEPARATOR);
                    this.requestBody.put(keyValue[0], keyValue[1]);
                }
            }
            if(requestLine.isEmpty()){
                bodyBegin = true;
            }
        }

    }

    @Override
    public HttpMethod getMethod() {
        return this.httpMethod;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.requestHeaders);
    }

    @Override
    public Map<String, String> getBodyParametars() {
        return Collections.unmodifiableMap(this.requestBody);
    }

    @Override
    public boolean isResource() {
        return this.requestUrl.contains(HTTPConstants.DOT_SEPARATOR);
    }

    @Override
    public void addHeader(String headerName, String headerValue) {
        this.requestHeaders.put(headerName, headerValue);
    }

    @Override
    public void addBodyParameter(String key, String value) {
        this.requestBody.put(key, value);
    }

    @Override
    public void setMethod(HttpMethod method) {
        this.httpMethod = method;
    }

    @Override
    public void setRequestUrl(String url) {
        this.requestUrl = url;
    }
}
