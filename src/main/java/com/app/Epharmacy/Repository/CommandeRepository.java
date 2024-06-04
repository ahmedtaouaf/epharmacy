package com.app.Epharmacy.Repository;

import com.app.Epharmacy.Entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query("SELECT count(c) FROM Commande c")
    int nbrCommandes();
    @Query("SELECT SUM(c.total) FROM Commande c")
    Double totalRevenues();
    @Query("SELECT SUM(c.nbrproduit) FROM Commande c")
    int nbrproduitsvendus();

    @Query("SELECT CAST(c.orderDate AS date), COUNT(c) FROM Commande c GROUP BY CAST(c.orderDate AS date)")
    List<Object[]> findOrderCountByDay();

    @Query(value = "SELECT m.name AS name, " +
            "m.price AS price, " +
            "COUNT(ca.medicament_id) AS soldCount, " +
            "SUM(m.price) AS totalRevenue " +
            "FROM commandeart ca " +
            "JOIN medicament m ON ca.medicament_id = m.id " +
            "GROUP BY ca.medicament_id " +
            "ORDER BY soldCount DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> top5medicaments();


}

