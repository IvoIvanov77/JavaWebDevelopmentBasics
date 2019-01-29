package javache.http;

import javache.constants.HttpConstants;
import javache.enums.HttpStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpResponseImpl implements HttpResponce {
    private HttpStatus statusCode;

    private HashMap<String, String> headers;

    private byte[] content;


    public HttpResponseImpl() {
        this.setContent(new byte[0]);
        this.headers = new HashMap<>();
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public HttpStatus getStatusCode() {
        return this.statusCode;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public byte[] getBytes() {
        byte[] headerBytes = this.getHeaderBytes();
        byte[] bodyBytes = this.getContent();
        byte[] fullResponse = new byte[headerBytes.length + bodyBytes.length];

        System.arraycopy(headerBytes, 0, fullResponse, 0, headerBytes.length);
        System.arraycopy(bodyBytes, 0, fullResponse, headerBytes.length, bodyBytes.length);
        return fullResponse;
    }

    @Override
    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    private byte[] getHeaderBytes(){
        StringBuilder result = new StringBuilder()
                .append(this.statusCode.getAsResponse())
                .append(HttpConstants.SEPARATOR_LINE_RESPONSE);

        for (Map.Entry<String, String> header : headers.entrySet()) {
            result
                    .append(String.format("%s%s%s",
                            header.getKey(),
                            HttpConstants.SEPARATOR_HEADER_KVP,
                            header.getValue()))
                    .append(HttpConstants.SEPARATOR_LINE_RESPONSE);
        }
        result.append(HttpConstants.SEPARATOR_LINE_RESPONSE);

        return result.toString().getBytes(HttpConstants.SERVER_ENCODING);

    }
}
