package org.softuni.parsing_http_requests;

public enum HttpStatus {

    OK(200, "OK", "Success"),

    BAD_REQUEST(400, "Bad Request",
            "There was an error with the requested functionality due to malformed request."),

    UNAUTHORIZED(401, "Unauthorized",
            "You are not authorized to access the requested functionality."),

    NOT_FOUND(404, "Not Found",
            "The requested functionality was not found.");

    private int statusCode;
    private String statusPhrase;
    private String statusResponse;

    HttpStatus(int code, String name, String description) {
        this.statusCode = code;
        this.statusPhrase = name;
        this.statusResponse = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusPhrase() {
        return statusPhrase;
    }

    public String getDescription() {
        return statusResponse;
    }
}
