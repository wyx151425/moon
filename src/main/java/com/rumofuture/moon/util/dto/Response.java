package com.rumofuture.moon.util.dto;

public class Response {

    private static final int SUCCESS = 1;
    private static final int ERROR = 0;

    private int status;
    private String message;

    public Response(boolean isSuccess, String message) {
        this.status = isSuccess ? SUCCESS : ERROR;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
