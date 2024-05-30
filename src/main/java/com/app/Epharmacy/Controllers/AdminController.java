package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.*;
import com.app.Epharmacy.Repository.CommandeRepository;
import com.app.Epharmacy.Repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {



    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private MedicationRepository medicationRepository;

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
        commandeRepository.save(commande);
        return "redirect:/admin/commandes";
    }








}

