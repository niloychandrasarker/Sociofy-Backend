package com.niloy.response;

public class AuthRespose {
    private String token;
    private String message;

    public AuthRespose() {

    }

    public AuthRespose(String token, String message) {
        super();
        this.token = token;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
