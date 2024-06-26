package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.ClientInfo;
import com.app.Epharmacy.Entity.Login;
import com.app.Epharmacy.Entity.Medicament;
import com.app.Epharmacy.Repository.LoginRepository;
import com.app.Epharmacy.Services.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Map;

@Controller
public class CartController {
    private final CartService cartService;
    private final LoginRepository loginRepository;

    public CartController(CartService cartService, LoginRepository loginRepository) {
        this.cartService = cartService;
        this.loginRepository = loginRepository;
    }

    @GetMapping("/cart")
    public String viewCart(Model model, Authentication authentication) {
        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();

        BigDecimal subtotal = calculateSubtotal(cartItems);
        BigDecimal total = calculateTotal(subtotal);

        // Check if user is authenticated
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();

        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("total", total);
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("cartSize", cartSize);
        return "cart";
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
}
