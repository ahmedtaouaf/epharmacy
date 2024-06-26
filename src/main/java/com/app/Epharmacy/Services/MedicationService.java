package com.app.Epharmacy.Services;

import com.app.Epharmacy.Entity.Medicament;
import com.app.Epharmacy.Repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<Medicament> getAllMedications() {
        return medicationRepository.findAll();
    }
    public List<Medicament> searchMedications(String keyword) {
        return medicationRepository.findByNameContainingIgnoreCase(keyword);
    }

    public Optional<Medicament> getMedicationById(Long id) {
        return medicationRepository.findById(id);
    }

    public Medicament addMedication(Medicament medicament) {
        return medicationRepository.save(medicament);
    }

    public void updateMedication(Medicament medication) {
        Medicament existingMedication = medicationRepository.findById(medication.getId())
                .orElseThrow(() -> new RuntimeException("Medication not found"));

        existingMedication.setName(medication.getName());
        existingMedication.setDescription(medication.getDescription());
        existingMedication.setPrice(medication.getPrice());
        existingMedication.setImageFileName(medication.getImageFileName());

        medicationRepository.save(existingMedication);
    }

    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }
}
