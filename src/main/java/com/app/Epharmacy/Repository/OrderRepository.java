package com.app.Epharmacy.Repository;

import com.app.Epharmacy.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
