package com.galaxyNstudio.veggies.responseWrapper;

public class MobileExist {

    private boolean error;
    private String message;

    public MobileExist(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
