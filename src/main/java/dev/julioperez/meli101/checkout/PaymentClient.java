package dev.julioperez.meli101.checkout;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import dev.julioperez.meli101.checkout.dto.client.PaymentResponse;
import dev.julioperez.meli101.checkout.dto.generatePayment.PaymentRequest;
import dev.julioperez.meli101.checkout.dto.generatePayment.PaymentRequestResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Arrays;

@Service
public class PaymentClient {

    private static final String ACCESS_TOKEN = "TEST-5443327106848656-081120-c0c015c39ea3fbd6073d3e689ed89383-325398446";
    private static final String HOST = "https://api.mercadopago.com";
    private static final String GET_PAYMENT_TYPE = "/v1/payment_methods";

    private final HttpClient httpClient = HttpClient
            .newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();



    public String setupClient4() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Bearer ".concat(ACCESS_TOKEN))
                .uri(URI.create("https://api.mercadopago.com/v1/payment_methods"))
                .build();
        java.net.http.HttpResponse<String> response = httpClient.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String setupClient5() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Bearer ".concat(ACCESS_TOKEN))
                .uri(URI.create("http://localhost:8080/payment"))
                .build();

        java.net.http.HttpResponse<String> response = httpClient.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public PaymentResponse[] setupClient6() throws IOException, InterruptedException {


        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Bearer ".concat(ACCESS_TOKEN))
                .uri(URI.create("http://localhost:8080/payment"))
                .build();

        java.net.http.HttpResponse<String> response = httpClient.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());


        PaymentResponse[] targetArray = new PaymentResponse[0];
        Object o = null;
        try{
//            o = JSONObject.stringToValue(response.body());
//            JSONObject jsonObject = new JSONObject(o);
            targetArray = new GsonBuilder().create().fromJson(response.body(), PaymentResponse[].class);
            System.out.println(Arrays.toString(targetArray));
            PaymentResponse fto = new PaymentResponse();

        }catch (Exception error){
            System.out.println(error);
        }
        System.out.println(response.body());
        return targetArray;
    }

    public PaymentRequestResponse createPayment (PaymentRequest paymentRequest) throws IOException, InterruptedException {

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = null;
        try{
            requestBody = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(paymentRequest);
        }catch (Exception error){
            System.out.println("error 1");
        }
//        HttpRequest.BodyPublisher bodyPublisher = null;
//        try{
//            bodyPublisher = (HttpRequest.BodyPublisher) paymentRequest;
//        }catch (Exception error){
//            System.out.println("primer error");
//        }
        java.net.http.HttpResponse<String> response = null;
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer ".concat(ACCESS_TOKEN))
                    .uri(URI.create("https://api.mercadopago.com/v1/payments"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            response = httpClient.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        }catch (Exception error){
            System.out.println("segundo error");
        }

        PaymentRequestResponse paymentRequestResponse = new PaymentRequestResponse();

        try{

            paymentRequestResponse = new GsonBuilder().create().fromJson(response.body(), PaymentRequestResponse.class);

        }catch (Exception error){
            System.out.println("tercer error");
            System.out.println(error);
        }
        return paymentRequestResponse;
    }

}
