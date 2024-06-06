package com.app.Epharmacy.Repository;

import com.app.Epharmacy.Entity.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medicament, Long> {

    List<Medicament> findByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT *\n" +
            "FROM medicament\n" +
            "ORDER BY id DESC\n" +
            "LIMIT 6;\n", nativeQuery = true)
    List<Object[]> listLastmedicaments();

    @Query(value = "SELECT m.id as id, m.name AS name,\n" +
            "            m.price AS price, \n" +
            "            m.image_file_name AS filename,\n" +
            "            COUNT(ca.medicament_id) AS soldCount\n" +
            "            FROM commandeart ca \n" +
            "            JOIN medicament m ON ca.medicament_id = m.id \n" +
            "            GROUP BY ca.medicament_id \n" +
            "            ORDER BY soldCount DESC \n" +
            "            LIMIT 6", nativeQuery = true)
    List<Object[]> top6medicaments();
}
