package com.app.Epharmacy.Repository;

import com.app.Epharmacy.Entity.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientInfoRepository extends JpaRepository<ClientInfo, Long> {
    @Query("SELECT count(c) FROM ClientInfo c")
    int nbrClient();

    ClientInfo findByEmail(String email);
}
