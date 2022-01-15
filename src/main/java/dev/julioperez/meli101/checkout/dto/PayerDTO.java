package dev.julioperez.meli101.checkout.dto;

//import com.mercadopago.core.annotations.validation.NotNull;

import javax.validation.constraints.NotNull;

public class PayerDTO {
    @NotNull
    private String email;

    @NotNull
    private PayerIdentificationDTO identification;

    public PayerDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PayerIdentificationDTO getIdentification() {
        return identification;
    }

    public void setIdentification(PayerIdentificationDTO identification) {
        this.identification = identification;
    }
}
