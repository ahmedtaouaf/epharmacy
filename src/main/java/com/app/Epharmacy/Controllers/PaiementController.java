package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.Medicament;
import com.app.Epharmacy.Services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.Map;

@Controller
public class PaiementController {

    private final CartService cartService;
    public PaiementController( CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/checkout")
    public String paiementPage(Model model){
        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        // Calculate subtotal and total
        BigDecimal subtotal = calculateSubtotal(cartItems);
        BigDecimal total = calculateTotal(subtotal);


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


        return "position";
    }
    @GetMapping("/positiontest")
    public String positionPagetest(Model model){


        return "positiontest";
    }

}
