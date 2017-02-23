package com.brahalla.Cerberus.model.json.response;

/**
 * Created by dani on 10/5/2016.
 */
public class CreateCardResponse {
    private String cardId;

    public CreateCardResponse(String cardId) {
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
