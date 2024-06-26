package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.*;
import com.app.Epharmacy.Repository.*;
import com.app.Epharmacy.Services.CartService;
import com.app.Epharmacy.Services.EmailService;
import com.app.Epharmacy.Services.PdfService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class PaiementController {

    private final CartService cartService;
    private final PharmacieRepository pharmacieRepository;
    private final ClientInfoRepository clientInfoRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final EmailService emailService;
    private final PdfService pdfService;
    private final NotificationController notificationController;
    private final LoginRepository loginRepository;


    public PaiementController(CartService cartService, PharmacieRepository pharmacieRepository, ClientInfoRepository clientInfoRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, EmailService emailService, PdfService pdfService, NotificationController notificationController, LoginRepository loginRepository) {
        this.cartService = cartService;
        this.pharmacieRepository = pharmacieRepository;
        this.clientInfoRepository = clientInfoRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.emailService = emailService;
        this.pdfService = pdfService;
        this.notificationController = notificationController;
        this.loginRepository = loginRepository;
    }

    @GetMapping("/checkout")
    public String paiementPage(Model model, RedirectAttributes redirectAttributes, Authentication authentication){
        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        // Calculate subtotal and total
        BigDecimal subtotal = calculateSubtotal(cartItems);
        BigDecimal total = calculateTotal(subtotal);

        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();

        model.addAttribute("isAuthenticated", isAuthenticated);

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
    public String positionPage(Model model,Authentication authentication) {
            Map<Long, Medicament> cartItems = cartService.getCartItems();
            int cartSize = cartItems.size();
            BigDecimal subtotal = calculateSubtotal(cartItems);
            BigDecimal total = calculateTotal(subtotal);
            boolean isAuthenticated = authentication != null && authentication.isAuthenticated();

            model.addAttribute("isAuthenticated", isAuthenticated);

            model.addAttribute("cartItems", cartItems);
            model.addAttribute("subtotal", subtotal);
            model.addAttribute("total", total);
            model.addAttribute("cartSize", cartSize);
            model.addAttribute("pharmacies", pharmacieRepository.findAll());

            return "position";
    }


    @PostMapping("/confirm")
    public String confirmOrder(Model model,
                               @RequestParam Long pharmacyId) {

        Pharmacie pharmacie = pharmacieRepository.findById(pharmacyId).orElse(null);
        if (pharmacie == null) {
            return "redirect:/position";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        Login login = loginRepository.findByUsername(username);

        ClientInfo clientInfo = login.getClientInfo();



        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        BigDecimal subtotal = calculateSubtotal(cartItems);
        BigDecimal total = calculateTotal(subtotal);

        Commande commande = new Commande();
        commande.setClientInfo(clientInfo);
        commande.setPharmacie(pharmacie);
        commande.setOrderDate(new Date());
        commande.setTotal(total);
        commande.setNbrproduit(cartSize);
        commande.setStatus(false);
        orderRepository.save(commande);

        for (Map.Entry<Long, Medicament> entry : cartItems.entrySet()) {
            Medicament medicament = entry.getValue();
            Commandeart commandeart = new Commandeart();
            commandeart.setCommande(commande);
            commandeart.setMedicamentId(medicament.getId());
            orderItemRepository.save(commandeart);
        }

        byte[] pdfBytes = pdfService.generateInvoice(clientInfo, pharmacie, commande, cartItems);

        try {

            Map<String, Object> variables = new HashMap<>();
            variables.put("clientName", commande.getClientInfo().getFirstName() + " " + commande.getClientInfo().getLastName());
            variables.put("medications", cartItems.values());
            variables.put("commandetotal", commande.getTotal());
            emailService.sendInvoice(clientInfo.getEmail(), "Votre bon de commande E-Pharmacy",variables, pdfBytes);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        notificationController.sendOrderNotification(commande);
        boolean isAuthenticated =  authentication.isAuthenticated();

        model.addAttribute("isAuthenticated", isAuthenticated);

        model.addAttribute("pharmacie", pharmacie);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("total", total);
        model.addAttribute("cartSize", cartSize);
        model.addAttribute("commande", commande);

        return "thankyou";
    }



}


