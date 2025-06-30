package com.example.medcentral.model.response;


public enum CustomResponseCode {
    OK(200, "OK"),
    CREATED(201, "Created"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
    // add more as needed

    private final int statusCode;
    private final String statusCodeDescription;

    CustomResponseCode(int statusCode, String statusCodeDescription) {
        this.statusCode = statusCode;
        this.statusCodeDescription = statusCodeDescription;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusCodeDescription() {
        return statusCodeDescription;
    }
}

