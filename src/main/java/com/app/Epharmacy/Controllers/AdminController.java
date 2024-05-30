package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.Commande;
import com.app.Epharmacy.Entity.Pharmacie;
import com.app.Epharmacy.Repository.CommandeRepository;
import com.app.Epharmacy.Services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {



    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private CommandeService commandeService;

    @GetMapping("/admin/commandes")
    public String dashboardPage(Model model){

        List<Commande> commande = commandeRepository.findAll();
        model.addAttribute("commandes",commande);


        return "admin/admin-orders";
    }

    @GetMapping("/admin/commandes/{id}/details")
    @ResponseBody
    public List<Object[]> getCommandeDetails(@PathVariable Long id) {

        return commandeService.getClientInfoAndItems(id);
    }



}

