package com.app.Epharmacy.Repository;

import com.app.Epharmacy.Entity.Commande;
import com.app.Epharmacy.Entity.Commandeart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
