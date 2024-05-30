package com.app.Epharmacy.Repository;

import com.app.Epharmacy.Entity.Commande;
import com.app.Epharmacy.Entity.Commandeart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query("SELECT c.clientInfo, ca FROM Commande c JOIN c.commandearts ca WHERE c.id = :commandeId")
    List<Object[]> findClientInfoAndItemsByCommandeId(@Param("commandeId") Long commandeId);

}

