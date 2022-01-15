package dev.julioperez.meli101.checkout;

import dev.julioperez.meli101.checkout.dto.client.PaymentResponse;
import dev.julioperez.meli101.checkout.dto.generatePayment.PaymentRequest;
import dev.julioperez.meli101.checkout.dto.generatePayment.PaymentRequestResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class PaymentServiceImplementation {

    private final PaymentClient paymentClient;

    public PaymentServiceImplementation(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    public String getAllMethodTypes() throws IOException, URISyntaxException, InterruptedException {
        return paymentClient.setupClient4();

    }

    public PaymentResponse[] getDocumentTypes() throws IOException, URISyntaxException, InterruptedException {
        PaymentResponse[] valor = paymentClient.setupClient6();
//        System.out.println(valor);
        System.out.println(valor[0].getSettings()[0].getCard_number().getValidation());
        return valor;

    }

    public PaymentRequestResponse createPayment(PaymentRequest paymentRequest) throws IOException, InterruptedException {
        return paymentClient.createPayment(paymentRequest);
    }

}
