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

    public Optional<Medicament> getMedicationById(Long id) {
        return medicationRepository.findById(id);
    }

    public Medicament addMedication(Medicament medicament) {
        return medicationRepository.save(medicament);
    }

    public Medicament updateMedication(Long id, Medicament medicament) {
        medicament.setId(id);
        return medicationRepository.save(medicament);
    }

    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }
}
