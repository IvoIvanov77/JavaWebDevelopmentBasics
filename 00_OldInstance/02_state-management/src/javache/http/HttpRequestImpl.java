package javache.http;

import javache.Constants.HTTPConstants;
import javache.enums.HttpMethod;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestImpl implements HttpRequest {

    private String requestUrl;

    private HttpMethod httpMethod;

    private Map<String, String> requestHeaders;

    private Map<String, String> requestBody;

    private Map<String, HttpCookie> cookies;

    public HttpRequestImpl(String requestContent) {
        this.initMethod(requestContent);
        this.initRequestUrl(requestContent);
        this.initHeaders(requestContent);
        this.initCookies();
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

    private void initCookies() {
        if(!this.requestHeaders.containsKey("Cookie")){
            return;
        }

        this.cookies = new HashMap<>();

        String[] cookiesStringArray = this.requestHeaders.get("Cookie")
                .split(HTTPConstants.SEMICOLON_SEPARATOR);

        for (String cookieAsString : cookiesStringArray) {
            String[] keyValue = cookieAsString.split("=");
            HttpCookie cookie = new HttpCookieImpl(keyValue[0], keyValue[1]);
            this.cookies.putIfAbsent(cookie.getName(), cookie);
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
                String line = URLDecoder.decode(requestLine, StandardCharsets.UTF_8);
                String[] bodyParams = line.split(HTTPConstants.BODY_PARAMS_SEPARATOR);
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
    public Map<String, String> getRequestHeaders() {
        return Collections.unmodifiableMap(this.requestHeaders);
    }

    @Override
    public Map<String, String> getRequestBody() {
        return Collections.unmodifiableMap(this.requestBody);
    }

    @Override
    public Map<String, HttpCookie> getCookies() {
        return Collections.unmodifiableMap(this.cookies);
    }

    @Override
    public boolean isResource() {
        return this.requestUrl.contains(HTTPConstants.DOT_SEPARATOR);
    }
}
