package dev.julioperez.meli101.checkout;

import dev.julioperez.meli101.checkout.dto.CardPaymentDTO;
import dev.julioperez.meli101.checkout.dto.PaymentResponseDTO;
import dev.julioperez.meli101.checkout.dto.client.PaymentResponse;
import dev.julioperez.meli101.checkout.dto.generatePayment.PaymentRequest;
import dev.julioperez.meli101.checkout.dto.generatePayment.PaymentRequestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/process_payment")
public class PaymentController {

    private final PaymentServiceImplementation paymentServiceImplementation;
    private final CardPaymentService cardPaymentService;

    public PaymentController(PaymentServiceImplementation paymentServiceImplementation, CardPaymentService cardPaymentService) {
        this.paymentServiceImplementation = paymentServiceImplementation;
        this.cardPaymentService = cardPaymentService;
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    //@GetMapping
    public String getAllPaymentMethods() throws IOException, URISyntaxException, InterruptedException {
        return paymentServiceImplementation.getAllMethodTypes();
    }

    //@GetMapping(value = "/getdto", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/getdto")
    //@GetMapping
    public List<PaymentResponse> getDocumentTypes() throws IOException, URISyntaxException, InterruptedException {
        List<PaymentResponse> response = List.of(paymentServiceImplementation.getDocumentTypes());
        //Optional<PaymentResponse> response = Arrays.stream(paymentServiceImplementation.getDocumentTypes()).findFirst();
        return response;
    }

    @PostMapping("/create")
    public PaymentRequestResponse createPayment(@RequestBody PaymentRequest paymentRequest) throws IOException, InterruptedException {
        return paymentServiceImplementation.createPayment(paymentRequest);
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> processPayment(@RequestBody @Valid CardPaymentDTO cardPaymentDTO) {
        PaymentResponseDTO payment = cardPaymentService.processPayment(cardPaymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }
}
