package org.concordion.ext.service.listener;

public class SuccessResponseEvent {
    private String responseMessage;

    public SuccessResponseEvent(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}