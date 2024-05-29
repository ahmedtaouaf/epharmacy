package com.app.Epharmacy.Repository;

import com.app.Epharmacy.Entity.Commandeart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<Commandeart, Long> {
}
