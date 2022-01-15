package dev.julioperez.meli101.checkout.dto.client;

public class PaymentSettingResponse {

    public PaymentValidation getCard_number() {
        return card_number;
    }

    public void setCard_number(PaymentValidation card_number) {
        this.card_number = card_number;
    }

    private PaymentValidation card_number;
}


