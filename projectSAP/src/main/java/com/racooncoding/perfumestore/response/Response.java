package com.racooncoding.perfumestore.response;

public class Response {
    private String message;
    private String redirectUrl;

    public Response(String message) {
        this.message = message;
    }

    public Response(String message, String redirectUrl) {
        this.message = message;
        this.redirectUrl = redirectUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                '}';
    }
}
