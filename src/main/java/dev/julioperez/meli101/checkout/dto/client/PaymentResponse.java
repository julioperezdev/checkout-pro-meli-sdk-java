package dev.julioperez.meli101.checkout.dto.client;

public class PaymentResponse {

    private String name;
    private String thumbnail;
    private String payment_type_id;

    public PaymentSettingResponse[] getSettings() {
        return settings;
    }

    public void setSettings(PaymentSettingResponse[] settings) {
        this.settings = settings;
    }

    private PaymentSettingResponse[] settings;


    public String getPayment_type_id() {
        return payment_type_id;
    }

    public void setPayment_type_id(String payment_type_id) {
        this.payment_type_id = payment_type_id;
    }
//    private List<Object> settings;
//
//    public List<Object> getSettings() {
//        return settings;
//    }
//
//    public void setSettings(List<Object> settings) {
//        this.settings = settings;
//    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
