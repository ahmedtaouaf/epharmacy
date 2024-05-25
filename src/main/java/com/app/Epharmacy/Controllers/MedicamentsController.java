package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.Medicament;
import com.app.Epharmacy.Services.CartService;
import com.app.Epharmacy.Services.MedicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class MedicamentsController {
    private final MedicationService medicationService;
    private final CartService cartService;
    private final Path imageStoragePath = Paths.get("src/main/resources/static/images");

    public MedicamentsController(MedicationService medicationService, CartService cartService) {
        this.medicationService = medicationService;
        this.cartService = cartService;
    }

    @GetMapping("/medicaments")
    public String medicamentpage(Model model){
        List<Medicament> medicaments = medicationService.getAllMedications();
        model.addAttribute("medications", medicaments);
        return "shop";
    }

    @GetMapping("/medicaments/{id}")
    public String getMedicationById(@PathVariable Long id, Model model) {
        Optional<Medicament> medication = medicationService.getMedicationById(id);
        if (medication.isPresent()) {
            model.addAttribute("medication", medication.get());

            return "shop-single";
        } else {
            return "redirect:/medications";
        }
    }

    @PostMapping("/medicaments/{id}/add-to-cart")
    public String addToCart(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Medicament> medication = medicationService.getMedicationById(id);
        if (medication.isPresent()) {
            cartService.addToCart(medication.get());
            redirectAttributes.addFlashAttribute("successMessage", "Medicament added to cart!");
        }
        return "redirect:/medicaments/" + id;
    }

}
