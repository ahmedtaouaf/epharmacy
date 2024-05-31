package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.Medicament;
import com.app.Epharmacy.Repository.MedicationRepository;
import com.app.Epharmacy.Services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class StockController {

    @Autowired
    private MedicationService medicationService;
    private final Path imageStoragePath = Paths.get("src/main/resources/static/images");

    @GetMapping("/admin/stocklist")
    public String stockliste(Model model){

        List<Medicament> medicaments = medicationService.getAllMedications();
        model.addAttribute("medicaments", medicaments);

        return "admin/stock-produits";
    }

    @GetMapping("/admin/addproduit")
    public String showAddMedicationForm(Model model) {
        model.addAttribute("medication", new Medicament());
        return "/admin/stock-addproduit";
    }

    @PostMapping("/addmedicamenet")
    public String addMedication(@ModelAttribute Medicament medicament, @RequestParam("imageFile") MultipartFile imageFile, RedirectAttributes redirectAttributes) {
        if (!imageFile.isEmpty()) {
            try {
                String imageFileName = imageFile.getOriginalFilename();
                imageFile.transferTo(new File(imageStoragePath.resolve(imageFileName).toString()));
                medicament.setImageFileName(imageFileName);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception
            }
        }
        medicationService.addMedication(medicament);
        return "redirect:/shop";
    }





}
