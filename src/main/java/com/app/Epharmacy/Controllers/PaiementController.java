package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.Medicament;
import com.app.Epharmacy.Entity.Pharmacie;
import com.app.Epharmacy.Repository.PharmacieRepository;
import com.app.Epharmacy.Services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
public class PaiementController {

    private final CartService cartService;
    private final PharmacieRepository pharmacieRepository;
    public PaiementController( CartService cartService, PharmacieRepository pharmacieRepository) {
        this.cartService = cartService;
        this.pharmacieRepository = pharmacieRepository;
    }

    @GetMapping("/checkout")
    public String paiementPage(Model model, RedirectAttributes redirectAttributes){
        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        // Calculate subtotal and total
        BigDecimal subtotal = calculateSubtotal(cartItems);
        BigDecimal total = calculateTotal(subtotal);
        if (cartItems.isEmpty()){
            redirectAttributes.addFlashAttribute("panierVide", "Votre panier est actuellement vide, veuillez ajouter des produits");
            return "redirect:/cart";
        }


        model.addAttribute("cartItems", cartItems);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("total", total);
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("cartSize", cartSize);

        return "checkout";
    }

    private BigDecimal calculateSubtotal(Map<Long, Medicament> cartItems) {

        BigDecimal subtotal = BigDecimal.ZERO;
        for (Medicament medicament : cartItems.values()) {
            subtotal = subtotal.add(BigDecimal.valueOf(medicament.getPrice()));
        }
        return subtotal;
    }

    private BigDecimal calculateTotal(BigDecimal subtotal) {

        return subtotal;
    }



    @GetMapping("/position")
    public String positionPage(Model model){
        List<Pharmacie> pharmacies = pharmacieRepository.findAll();
        model.addAttribute("pharmacies", pharmacies);
        return "position";
    }


}
