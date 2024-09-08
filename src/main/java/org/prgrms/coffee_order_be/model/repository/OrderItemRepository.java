package org.prgrms.coffee_order_be.model.repository;

import org.prgrms.coffee_order_be.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
