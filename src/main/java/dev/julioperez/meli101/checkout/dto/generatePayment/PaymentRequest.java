package dev.julioperez.meli101.checkout.dto.generatePayment;

public class PaymentRequest {
    private Boolean binary_mode;
    private String description;
    //private PaymentItemsRequest[] items;
    private String payment_method_id;
    private Float transaction_amount;
    private Integer installments;
    private String statement_descriptor;


    public Boolean getBinary_mode() {
        return binary_mode;
    }

    public void setBinary_mode(Boolean binary_mode) {
        this.binary_mode = binary_mode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(String payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    public Float getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(Float transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public String getStatement_descriptor() {
        return statement_descriptor;
    }

    public void setStatement_descriptor(String statement_descriptor) {
        this.statement_descriptor = statement_descriptor;
    }
}
