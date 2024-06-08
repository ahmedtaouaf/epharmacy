package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.Commande;
import com.app.Epharmacy.Entity.Medicament;
import com.app.Epharmacy.Repository.MedicationRepository;
import com.app.Epharmacy.Services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class StockController {

    @Autowired
    private MedicationService medicationService;
    @Autowired
    private MedicationRepository medicationRepository;
    private final Path imageStoragePath = Paths.get("src/main/resources/static/images").toAbsolutePath();

    @GetMapping("/admin/stocklist")
    public String stockliste(Model model){

        List<Medicament> medicaments = medicationService.getAllMedications();
        model.addAttribute("medicaments", medicaments);

        return "admin/stock-produits";
    }

    @GetMapping("/admin/stock-addproduit")
    public String showAddMedicationForm(Model model) {
        model.addAttribute("medication", new Medicament());
        return "/admin/stock-addproduit";
    }

    @PostMapping("/admin/stock/{id}/delete")
    public String deleteStock(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        medicationService.deleteMedication(id);
        redirectAttributes.addFlashAttribute("produitDelete", "Médicament supprimer avec succès du stock !");
        return "redirect:/admin/stocklist";
    }

    @PostMapping("/addmedicamenet")
    public String addMedication(@ModelAttribute Medicament medicament, @RequestParam("imageFile") MultipartFile imageFile, RedirectAttributes redirectAttributes) {
        if (!imageFile.isEmpty()) {
            try {
                Files.createDirectories(imageStoragePath);

                String imageFileName = imageFile.getOriginalFilename();
                Path targetLocation = imageStoragePath.resolve(imageFileName);
                imageFile.transferTo(targetLocation.toFile());
                medicament.setImageFileName(imageFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        medicationService.addMedication(medicament);
        redirectAttributes.addFlashAttribute("produitAdd", "Médicament ajouté avec succès au stock !");
        return "redirect:/admin/stock-addproduit";
    }






}
