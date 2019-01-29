package javache.http;

import javache.Constants.HTTPConstants;
import javache.enums.HttpStatusCode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpResponseImpl implements HttpResponse {
    private HttpStatusCode status;

    private byte[] content;

    private Map<String, String> headers;

    public HttpResponseImpl() {
        this.headers = new HashMap<>();
        this.content = new byte[0];

    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public HttpStatusCode getStatusCode() {
        return this.status;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public byte[] getBytes() {
        StringBuilder result = new StringBuilder();
        result
                .append(this.getStatusCode().getStatusResponse())
                .append(HTTPConstants.LINE_SEPARATOR);
        this.headers.forEach((key, value) -> result
                .append(key)
                .append(HTTPConstants.COLON_SEPARATOR)
                .append(value)
                .append(HTTPConstants.LINE_SEPARATOR));

        byte[] headersBytes = result
                .append(HTTPConstants.LINE_SEPARATOR)
                .toString()
                .getBytes();

        byte[] fullResponse = new byte[headersBytes.length + this.content.length];

        System.arraycopy(headersBytes, 0, fullResponse, 0, headersBytes.length);
        System.arraycopy(this.content, 0, fullResponse, headersBytes.length, this.content.length);

        return fullResponse;
    }

    @Override
    public void setStatusCode(HttpStatusCode status) {
        this.status = status;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.putIfAbsent(header, value);
    }

    @Override
    public void setCookie(HttpCookie cookie) {
        this.addHeader("set-cookie", cookie.toString());
    }
}
