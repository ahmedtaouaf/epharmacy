package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.*;
import com.app.Epharmacy.Repository.ClientInfoRepository;
import com.app.Epharmacy.Repository.CommandeRepository;
import com.app.Epharmacy.Repository.MedicationRepository;
import com.app.Epharmacy.Services.CommandeService;
import com.app.Epharmacy.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AdminController {



    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private ClientInfoRepository clientInfoRepository;
    @Autowired
    private CommandeService commandeService;
    @Autowired
    private EmailService emailService;

    @GetMapping("/admin/commandes")
    public String dashboardPage(Model model){

        List<Commande> commande = commandeRepository.findAll();
        model.addAttribute("commandes",commande);


        return "admin/admin-orders";
    }

    @GetMapping("/admin/commandes/{id}/details")
    public String showOrderDetails(@PathVariable Long id, Model model) {
        Commande commande = commandeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid commande Id:" + id));
        List<Commandeart> commandearts = commande.getCommandearts();
        ClientInfo clientInfo = commande.getClientInfo();
        Pharmacie pharmacie = commande.getPharmacie();

        List<Long> medicamentIds = commandearts.stream().map(Commandeart::getMedicamentId).collect(Collectors.toList());
        List<Medicament> medications = medicationRepository.findAllById(medicamentIds);

        model.addAttribute("clientInfo", clientInfo);
        model.addAttribute("commandearts", commandearts);
        model.addAttribute("medications", medications);
        model.addAttribute("commande", commande);
        model.addAttribute("pharmacie", pharmacie);


        return "admin/order-details";
    }
    @GetMapping("/admin/commandes/{id}/confirm")
    public String confirmCommande(@PathVariable Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid commande Id:" + id));
        commande.setStatus(true);
        List<Commandeart> commandearts = commande.getCommandearts();


        commandeRepository.save(commande);

        try {
            Map<String, Object> variables = new HashMap<>();
            variables.put("clientName", commande.getClientInfo().getFirstName() + " " + commande.getClientInfo().getLastName());
            variables.put("pharmacienom", commande.getPharmacie().getNom());
            variables.put("pharmacieadresse", commande.getPharmacie().getAdresse());
            variables.put("pharmacieemail", commande.getPharmacie().getEmail());
            variables.put("pharmaciefix", commande.getPharmacie().getFix());
            variables.put("pharmacietelephone", commande.getPharmacie().getTelephone());
            variables.put("pharmacielatitude", commande.getPharmacie().getLatitude());
            variables.put("pharmacielongitude", commande.getPharmacie().getLongitude());

            // Add the Google Maps directions link
            String directionsUrl = "https://www.google.com/maps/dir/?api=1&destination=" +
                    commande.getPharmacie().getLatitude() + "," +
                    commande.getPharmacie().getLongitude();
            variables.put("directionsUrl", directionsUrl);

            emailService.sendConfirmation(commande.getClientInfo().getEmail(), "La pharmacie est confirmée votre commande", variables);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return "redirect:/admin/commandes";
    }

    @GetMapping("/admin/commandes/{id}/delete")
    public String deleteCommande(@PathVariable Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid commande Id:" + id));

        commandeRepository.delete(commande);

        return "redirect:/admin/commandes";
    }

    @GetMapping("/admin/dashboard")
    public String getDashboardPage(Model model) {

        int nbrClient=clientInfoRepository.nbrClient();
        int nbrCommandes=commandeRepository.nbrCommandes();
        double totalRevenues=commandeRepository.totalRevenues();
        int nbrproduitsvendus=commandeRepository.nbrproduitsvendus();
        List<Object[]> orderCounts = commandeService.getOrderCountByDay();
        model.addAttribute("orderCounts", orderCounts);
        List<Object[]> topMedicaments = commandeRepository.top5medicaments();
        List<Object[]> results = commandeRepository.findTop5PharmaciesWithMostOrders();
        List<Object[]> topPharmaciesByRevenue = commandeRepository.findTop4PharmaciesByRevenue();

        model.addAttribute("nbrClients", nbrClient);
        model.addAttribute("nbrCommandes", nbrCommandes);
        model.addAttribute("totalRevenues", totalRevenues);
        model.addAttribute("nbrproduitsvendus", nbrproduitsvendus);
        model.addAttribute("medicaments", topMedicaments);
        model.addAttribute("top5Pharmacies", results);
        model.addAttribute("topPharmaciesByRevenue", topPharmaciesByRevenue);
        return "admin/admin-dashboard";
    }












}

