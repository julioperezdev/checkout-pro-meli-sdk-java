package dev.julioperez.meli101.pro;

import com.mercadopago.exceptions.MPException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/preference")
public class ProController {

    private final ProService proService;

    public ProController(ProService proService) {
        this.proService = proService;
    }

    @PostMapping
    public void create() throws MPException {
        proService.generatePreference();
    }
}
