package javache.http;

import javache.constants.HttpConstants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestImpl implements HttpRequest {

    private HashMap<String, String> headers;

    private HashMap<String, String> bodyParameters;

    private String method;

    private String requestUrl;

    public HttpRequestImpl(String requestContent){
        this.initMethod(requestContent);
        this.initRequestUrl(requestContent);
        this.initBodyParameters(requestContent);
    }

    private void initMethod(String requestContent){
        this.setMethod(requestContent.split(HttpConstants.SEPARATOR_EMPTY_SPACE)[0]);
    }

    private void initRequestUrl(String requestContent){
        this.setRequestUrl(requestContent.split(HttpConstants.SEPARATOR_EMPTY_SPACE)[1]);
    }

    private void initHeaders(String requestContent){
        this.headers = new HashMap<>();

        String[] requestParams = requestContent.split(HttpConstants.SEPARATOR_LINE);

        for (int i = 1; i < requestParams.length; i++) {
            String line = requestParams[i];
            if(line.length() == 0){
                break;
            }
            String[] keyValuePair = line.split(HttpConstants.SEPARATOR_HEADER_KVP);
            this.addHeader(keyValuePair[0], keyValuePair[1]);
        }
    }

    private void initBodyParameters(String requestContent){
        if(this.getMethod().equalsIgnoreCase("POST")){
            this.bodyParameters = new HashMap<>();
            String[] requestParams = requestContent.split(HttpConstants.SEPARATOR_LINE);
            int headersSize = this.headers.size();
            if(requestParams.length > headersSize + 2){
                String[] bodyParams = requestParams[headersSize + 2]
                        .split(HttpConstants.SEPARATOR_BODY_PARAMS);
                for (String bodyParam : bodyParams) {
                    String[] keyValuePair = bodyParam.split(HttpConstants.SEPARATOR_BODY_KVP);
                    this.addBodyParameter(keyValuePair[0], keyValuePair[1]);
                }
            }

        }
    }

    // TODO: 12/17/2018 add initCookies   




    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return Collections.unmodifiableMap(this.bodyParameters);
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.put(parameter, value);
    }

    @Override
    public boolean isResource() {
        return this.requestUrl.contains(HttpConstants.SEPARATOR_DOT);
    }
}
