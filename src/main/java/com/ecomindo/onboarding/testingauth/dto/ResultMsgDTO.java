package com.ecomindo.onboarding.testingauth.dto;

public class ResultMsgDTO {
    private String message;
    private String token;

    public ResultMsgDTO(){

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ResultMsgDTO(String msg){
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    

}
