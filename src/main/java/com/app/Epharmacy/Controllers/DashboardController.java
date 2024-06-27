package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.*;
import com.app.Epharmacy.Repository.CommandeRepository;
import com.app.Epharmacy.Repository.LoginRepository;
import com.app.Epharmacy.Repository.MedicationRepository;
import com.app.Epharmacy.Services.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    private final CartService cartService;
    private final MedicationRepository medicationRepository;
    private final LoginRepository loginRepository;
    private final CommandeRepository commandeRepository;

    public DashboardController(CartService cartService, MedicationRepository medicationRepository, LoginRepository loginRepository, CommandeRepository commandeRepository) {
        this.cartService = cartService;
        this.medicationRepository = medicationRepository;
        this.loginRepository = loginRepository;
        this.commandeRepository = commandeRepository;
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
        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication != null && authentication.isAuthenticated()) {

            String username = authentication.getName();
            Login login = loginRepository.findByUsername(username);


            if (login != null) {

                ClientInfo clientInfo = login.getClientInfo();
                List<Commande> listCommandes = commandeRepository.getCommandesByClients(clientInfo.getId());


                model.addAttribute("clientInfo", clientInfo);
                model.addAttribute("listCommandes", listCommandes);
            } else {

                return "redirect:/login";
            }


            model.addAttribute("isAuthenticated", true);
            model.addAttribute("cartSize", cartSize);

            return "profile";
        } else {

            return "redirect:/login";
        }
    }

    @GetMapping("/client/commandes/{id}/details")
    public String showOrderDetails(@PathVariable Long id, Model model) {
        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        Commande commande = commandeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid commande Id:" + id));
        List<Commandeart> commandearts = commande.getCommandearts();
        System.out.println(commandearts.size());
        ClientInfo clientInfo = commande.getClientInfo();
        Pharmacie pharmacie = commande.getPharmacie();
        List<Long> medicamentIds = commandearts.stream().map(Commandeart::getMedicamentId).collect(Collectors.toList());
        List<Medicament> medications = medicationRepository.findAllById(medicamentIds);

        model.addAttribute("cartSize", cartSize);
        model.addAttribute("clientInfo", clientInfo);
        model.addAttribute("commandearts", commandearts);
        model.addAttribute("medications", medications);
        model.addAttribute("commande", commande);
        model.addAttribute("pharmacie", pharmacie);


        return "commande-details";
    }


}
