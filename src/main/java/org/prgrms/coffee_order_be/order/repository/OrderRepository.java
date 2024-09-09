package org.prgrms.coffee_order_be.order.repository;

import java.util.List;
import java.util.UUID;
import org.prgrms.coffee_order_be.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, UUID> {

  List<Order> findByEmail(String email);

}
