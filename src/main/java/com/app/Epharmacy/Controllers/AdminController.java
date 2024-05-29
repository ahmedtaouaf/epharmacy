package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.Commande;
import com.app.Epharmacy.Entity.Pharmacie;
import com.app.Epharmacy.Repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private CommandeRepository commandeRepository;

    @GetMapping("/admin/commandes")
    public String dashboardPage(Model model){

        List<Commande> commande = commandeRepository.findAll();
        model.addAttribute("commandes",commande);


        return "admin/admin-orders";
    }
}
