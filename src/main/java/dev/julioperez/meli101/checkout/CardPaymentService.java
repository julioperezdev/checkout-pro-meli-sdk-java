package dev.julioperez.meli101.checkout;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Identification;
import com.mercadopago.resources.datastructures.payment.Payer;
import dev.julioperez.meli101.checkout.dto.CardPaymentDTO;
import dev.julioperez.meli101.checkout.dto.PaymentResponseDTO;
import dev.julioperez.meli101.checkout.exception.MercadoPagoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CardPaymentService {

    //private static final String ACCESS_TOKEN = "";
    //@Value("${mercado_pago_sample_access_token}")
    private String mercadoPagoAccessToken;

    public PaymentResponseDTO processPayment(CardPaymentDTO cardPaymentDTO) {
        try {
            MercadoPago.SDK.setAccessToken(mercadoPagoAccessToken);

            Payment payment = new Payment();

            payment.setTransactionAmount(cardPaymentDTO.getTransactionAmount())
                    .setToken(cardPaymentDTO.getToken())
                    .setDescription(cardPaymentDTO.getProductDescription())
                    .setInstallments(cardPaymentDTO.getInstallments())
                    .setPaymentMethodId(cardPaymentDTO.getPaymentMethodId());

            Identification identification = new Identification();
            identification.setType(cardPaymentDTO.getPayer().getIdentification().getType())
                    .setNumber(cardPaymentDTO.getPayer().getIdentification().getNumber());

            Payer payer = new Payer();
            payer.setEmail(cardPaymentDTO.getPayer().getEmail());
            payer.setIdentification(identification);

            payment.setPayer(payer);

            Payment createdPayment = payment.save();

            this.validatePaymentResult(createdPayment);

            PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO(
                    createdPayment.getId(),
                    String.valueOf(createdPayment.getStatus()),
                    createdPayment.getStatusDetail()
            );

            return paymentResponseDTO;
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            throw new MercadoPagoException(exception.getMessage());
        }
    }

    private void validatePaymentResult(Payment createdPayment) throws MPException {
        if(createdPayment.getId() == null) {
            String errorMessage = "Unknown error cause";

            if(createdPayment.getLastApiResponse() != null) {
                String sdkErrorMessage = createdPayment.getLastApiResponse().getJsonElementResponse().getAsJsonObject().get("message").getAsString();
                errorMessage = sdkErrorMessage != null ? sdkErrorMessage : errorMessage;
            }

            throw new MPException(errorMessage);
        }
    }
}
