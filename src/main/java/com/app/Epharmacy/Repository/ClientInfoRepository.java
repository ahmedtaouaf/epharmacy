package com.app.Epharmacy.Repository;

import com.app.Epharmacy.Entity.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientInfoRepository extends JpaRepository<ClientInfo, Long> {
}
