package com.app.Epharmacy.Repository;

import com.app.Epharmacy.Entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.repository.query.Param;
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

    @Query(value = "SELECT p.nom AS name, COUNT(c.id) AS orderCount " +
            "FROM commande c " +
            "JOIN pharmacie p ON c.pharmacie_id = p.id " +
            "GROUP BY p.id " +
            "ORDER BY orderCount DESC " +
            "LIMIT 4", nativeQuery = true)
    List<Object[]> findTop5PharmaciesWithMostOrders();

    @Query(value = "SELECT p.nom AS name, SUM(c.total) AS totalRevenue " +
            "FROM commande c " +
            "JOIN pharmacie p ON c.pharmacie_id = p.id " +
            "GROUP BY p.id " +
            "ORDER BY totalRevenue DESC " +
            "LIMIT 4", nativeQuery = true)
    List<Object[]> findTop4PharmaciesByRevenue();


    @Query("SELECT c " +
            "FROM Commande c " +
            "JOIN c.clientInfo i " +
            "WHERE c.clientInfo.id = :id order by c.orderDate DESC")
    List<Commande> getCommandesByClients(@Param("id") Long id);


}

