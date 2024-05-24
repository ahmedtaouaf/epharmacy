package com.app.Epharmacy.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MedicamentsController {

    @GetMapping("/medicaments")
    public String medicamentpage(){
        return "shop";
    }

}
