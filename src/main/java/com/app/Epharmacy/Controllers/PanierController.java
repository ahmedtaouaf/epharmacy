package com.app.Epharmacy.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PanierController {

    @GetMapping("/user/panier")
    public String panierpage(){
        return "cart";
    }
}
