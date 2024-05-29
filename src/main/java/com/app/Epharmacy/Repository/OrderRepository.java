package com.app.Epharmacy.Repository;

import com.app.Epharmacy.Entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Commande, Long> {
}
