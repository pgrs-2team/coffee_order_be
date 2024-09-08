package org.prgrms.coffee_order_be.model.repository;

import org.prgrms.coffee_order_be.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByEmail(String email);
}
