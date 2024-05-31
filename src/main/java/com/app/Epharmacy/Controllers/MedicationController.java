package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.Medicament;
import com.app.Epharmacy.Services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class MedicationController {
    private final MedicationService medicationService;
    private final Path imageStoragePath = Paths.get("src/main/resources/static/images");

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping("medications")
    public String getAllMedications(Model model) {

        return "list";
    }



    @GetMapping("/new")
    public String showAddMedicationForm(Model model) {
        model.addAttribute("medication", new Medicament());
        return "form";
    }

    @PostMapping("/addmedication")
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
        return "redirect:/medications";
    }

    @GetMapping("/edit/{id}")
    public String showEditMedicationForm(@PathVariable Long id, Model model) {
        Optional<Medicament> medication = medicationService.getMedicationById(id);
        if (medication.isPresent()) {
            model.addAttribute("medication", medication.get());
            return "medications/form";
        } else {
            return "redirect:/medications";
        }
    }

    @PostMapping("/{id}")
    public String updateMedication(@PathVariable Long id, @ModelAttribute Medicament medicament, @RequestParam("imageFile") MultipartFile imageFile, RedirectAttributes redirectAttributes) {
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
        medicationService.updateMedication(id, medicament);
        return "redirect:/medications";
    }

    @DeleteMapping("/{id}")
    public String deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
        return "redirect:/medications";
    }
}