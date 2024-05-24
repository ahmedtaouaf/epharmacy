package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.Medicament;
import com.app.Epharmacy.Services.MedicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class MedicamentsController {
    private final MedicationService medicationService;
    private final Path imageStoragePath = Paths.get("src/main/resources/static/images");

    public MedicamentsController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping("/medicaments")
    public String medicamentpage(Model model){
        List<Medicament> medicaments = medicationService.getAllMedications();
        model.addAttribute("medications", medicaments);
        return "shop";
    }

}
