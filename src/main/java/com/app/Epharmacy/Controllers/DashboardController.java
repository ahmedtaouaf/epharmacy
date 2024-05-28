package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.Medicament;
import com.app.Epharmacy.Services.CartService;
import com.app.Epharmacy.Services.MedicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class DashboardController {

    private final CartService cartService;
    public DashboardController( CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String mainpage(Model model){

        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        model.addAttribute("cartSize", cartSize);
        return "index";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/apropos")
    public String aPropos(Model model) {
        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        model.addAttribute("cartSize", cartSize);
        return "about";
    }
    @GetMapping("/contact")
    public String contactPage(Model model) {
        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        model.addAttribute("cartSize", cartSize);
        return "contact";
    }





}
