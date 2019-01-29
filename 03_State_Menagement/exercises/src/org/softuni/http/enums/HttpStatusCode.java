package org.softuni.http.enums;

import org.softuni.http.constans.HTTPConstants;

public enum HttpStatusCode {
    OK(200, "OK"),
    CREATED(201, "Created"),
    NO_CONTENT(204, "No Content"),
    SEE_OTHER(303, "See Other"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final String statusPhrase;
    private final String statusResponse;
    private final int statusCode;

    HttpStatusCode(int statusCode, String statusPhrase) {
        this.statusPhrase = statusPhrase;
        this.statusCode = statusCode;
        this.statusResponse = String.format("%s %d %s", HTTPConstants.HTTP_VERSION, this.statusCode, this.statusPhrase);
    }

    public String getStatusPhrase() {
        return statusPhrase;
    }

    public String getStatusResponse() {
        return statusResponse;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
