package com.app.Epharmacy.Repository;

import com.app.Epharmacy.Entity.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medicament, Long> {
}
