package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.*;
import com.app.Epharmacy.Repository.ClientInfoRepository;
import com.app.Epharmacy.Repository.OrderItemRepository;
import com.app.Epharmacy.Repository.OrderRepository;
import com.app.Epharmacy.Repository.PharmacieRepository;
import com.app.Epharmacy.Services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Controller
public class PaiementController {

    private final CartService cartService;
    private final PharmacieRepository pharmacieRepository;
    private final ClientInfoRepository clientInfoRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    public PaiementController(CartService cartService, PharmacieRepository pharmacieRepository, ClientInfoRepository clientInfoRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.cartService = cartService;
        this.pharmacieRepository = pharmacieRepository;
        this.clientInfoRepository = clientInfoRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
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

    @Controller
    public class OrderController {

        @GetMapping("/position")
        public String positionPage(Model model) {
            Map<Long, Medicament> cartItems = cartService.getCartItems();
            int cartSize = cartItems.size();
            BigDecimal subtotal = calculateSubtotal(cartItems);
            BigDecimal total = calculateTotal(subtotal);
            System.out.println(total);
            System.out.println(cartSize);

            model.addAttribute("cartItems", cartItems);
            model.addAttribute("subtotal", subtotal);
            model.addAttribute("total", total);
            model.addAttribute("cartSize", cartSize);
            model.addAttribute("pharmacies", pharmacieRepository.findAll());

            return "position";
        }

        @PostMapping("/confirm")
        public String confirmOrder(Model model, @RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam String address,
                                   @RequestParam String phone,
                                   @RequestParam String email,
                                   @RequestParam Long pharmacyId) {

            Pharmacie pharmacie = pharmacieRepository.findById(pharmacyId).orElse(null);
            if (pharmacie == null) {
                return "redirect:/position";
            }

            ClientInfo clientInfo = new ClientInfo();
            clientInfo.setFirstName(firstName);
            clientInfo.setLastName(lastName);
            clientInfo.setAddress(address);
            clientInfo.setPhone(phone);
            clientInfo.setEmail(email);
            clientInfoRepository.save(clientInfo);

            Map<Long, Medicament> cartItems = cartService.getCartItems();
            int cartSize = cartItems.size();
            // Calculate subtotal and total
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

            cartItems.clear();

            model.addAttribute("pharmacie", pharmacie);
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("subtotal", subtotal);
            model.addAttribute("total", total);
            model.addAttribute("cartSize", cartSize);

            return "thankyou";
        }


    }



    /*@GetMapping("/position")
    public String positionPage(Model model){
        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        List<Pharmacie> pharmacies = pharmacieRepository.findAll();
        model.addAttribute("cartSize", cartSize);
        model.addAttribute("pharmacies", pharmacies);
        return "position";
    }
    @PostMapping("/confirm")
    public String confirmClosestPharmacy(@RequestParam("pharmacyId") Long pharmacyId, Model model) {
        Map<Long, Medicament> cartItems = cartService.getCartItems();
        int cartSize = cartItems.size();
        cartItems.clear();
        Pharmacie pharmacy = pharmacieRepository.findById(pharmacyId).orElse(null);
        model.addAttribute("pharmacy", pharmacy);
        model.addAttribute("cartSize", cartSize);
        return "thankyou";
    }*/


}
