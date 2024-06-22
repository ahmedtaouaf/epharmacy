package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.Medicament;
import com.app.Epharmacy.Repository.MedicationRepository;
import com.app.Epharmacy.Services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    private final CartService cartService;
    private final MedicationRepository medicationRepository;

    public DashboardController(CartService cartService, MedicationRepository medicationRepository) {
        this.cartService = cartService;
        this.medicationRepository = medicationRepository;
    }

    @GetMapping("/")
    public String mainpage(Model model){
        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        List<Object[]> listLastmedicaments = medicationRepository.listLastmedicaments();
        List<Object[]> top6Medicaments = medicationRepository.top6medicaments();

        model.addAttribute("cartSize", cartSize);
        model.addAttribute("listLastmedicaments", listLastmedicaments);
        model.addAttribute("top6Medicaments", top6Medicaments);



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
