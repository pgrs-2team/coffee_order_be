package org.prgrms.coffee_order_be.model.repository;

import org.prgrms.coffee_order_be.model.entity.Order;
import org.prgrms.coffee_order_be.model.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByEmailOrderByCreatedAtDesc(String email);

    @Query("SELECT o FROM orders o WHERE o.orderStatus =:orderStatus")
    List<Order> findAllByOrderStatus(OrderStatus orderStatus);
}
