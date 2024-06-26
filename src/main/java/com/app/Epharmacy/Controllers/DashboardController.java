package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.ClientInfo;
import com.app.Epharmacy.Entity.Login;
import com.app.Epharmacy.Entity.Medicament;
import com.app.Epharmacy.Repository.LoginRepository;
import com.app.Epharmacy.Repository.MedicationRepository;
import com.app.Epharmacy.Services.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    private final CartService cartService;
    private final MedicationRepository medicationRepository;
    private final LoginRepository loginRepository;

    public DashboardController(CartService cartService, MedicationRepository medicationRepository, LoginRepository loginRepository) {
        this.cartService = cartService;
        this.medicationRepository = medicationRepository;
        this.loginRepository = loginRepository;
    }

    @GetMapping("/")
    public String mainpage(Model model, Authentication authentication){
        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        List<Object[]> listLastmedicaments = medicationRepository.listLastmedicaments();
        List<Object[]> top6Medicaments = medicationRepository.top6medicaments();


        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();

        model.addAttribute("isAuthenticated", isAuthenticated);
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
    public String aPropos(Model model, Authentication authentication) {

        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();

        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("cartSize", cartSize);
        return "about";
    }
    @GetMapping("/contact")
    public String contactPage(Model model,Authentication authentication) {
        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();

        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("cartSize", cartSize);
        return "contact";
    }

    @GetMapping("/profile")
    public String profilePage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication != null && authentication.isAuthenticated()) {

            String username = authentication.getName();
            Login login = loginRepository.findByUsername(username);

            if (login != null) {
                ClientInfo clientInfo = login.getClientInfo();
                model.addAttribute("clientInfo", clientInfo);
            } else {

                return "redirect:/login";
            }


            model.addAttribute("isAuthenticated", true);


            return "profile";
        } else {

            return "redirect:/login"; // Redirect to login page with error message
        }
    }


}
