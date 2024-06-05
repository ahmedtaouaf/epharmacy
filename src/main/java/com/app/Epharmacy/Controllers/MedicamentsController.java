package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.Medicament;
import com.app.Epharmacy.Services.CartService;
import com.app.Epharmacy.Services.MedicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
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
    public String medicamentPage(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        model.addAttribute("cartSize", cartSize);

        List<Medicament> medications;
        if (keyword != null && !keyword.isEmpty()) {
            medications = medicationService.searchMedications(keyword);
        } else {
            medications = medicationService.getAllMedications();
        }

        model.addAttribute("medications", medications);
        return "shop";
    }

    @GetMapping("/medicaments/{id}")
    public String getMedicationById(@PathVariable Long id, Model model) {

        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        model.addAttribute("cartSize", cartSize);
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
            redirectAttributes.addFlashAttribute("ajouterPanier", "Medicament bien ajouté au panier!");
        }
        return "redirect:/medicaments/" + id;
    }
    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        cartService.removeFromCart(id);
        redirectAttributes.addFlashAttribute("supprimerPanier", "Medicament bien supprimé du panier!");
        return "redirect:/cart";
    }

}
