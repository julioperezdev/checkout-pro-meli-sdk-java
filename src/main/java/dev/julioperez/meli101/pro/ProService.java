package dev.julioperez.meli101.pro;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.*;
import org.springframework.stereotype.Service;

@Service
public class ProService {

    public void generatePreference() throws MPException {
        MercadoPago.SDK.setAccessToken("");

        Preference preference = new Preference();
        preference.setBinaryMode(true);
        PaymentMethods paymentMethods = new PaymentMethods();
        ExcludedPaymentType excludedPaymentType = new ExcludedPaymentType();
        excludedPaymentType.setId("ticket");
        paymentMethods.appendExcludedPaymentTypes(excludedPaymentType);
        preference.setPaymentMethods(paymentMethods);

        Item item = new Item();
        item.setTitle("Spring Boot Professional")
                .setQuantity(1)
                .setUnitPrice((float) 2000)
                .setPictureUrl("https://i.imgur.com/K01zeD9.png")
                .setCurrencyId("ARS")
                .setCategoryId("learnings");

        Payer payer = new Payer();
        payer.setEmail("julio@email.com");
        payer.setName("Juliiio");
        payer.setSurname("Viloria");


        preference.setStatementDescriptor("Julioperez.dev");
        BackUrls backUrls = new BackUrls();
        backUrls.setSuccess("https://protobot.dev");
        backUrls.setFailure("http://lineaesperanza.com");

        preference.setAutoReturn(Preference.AutoReturn.approved);




        preference.appendItem(item);
        preference.setBackUrls(backUrls);
        preference.setPayer(payer);
        Preference preferenceSaved = preference.save();

        System.out.println(preferenceSaved);
        System.out.println(preferenceSaved.getSandboxInitPoint());
    }

}
