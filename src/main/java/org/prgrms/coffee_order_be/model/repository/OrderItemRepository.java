package org.prgrms.coffee_order_be.model.repository;

import org.prgrms.coffee_order_be.model.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repositor
public interface OrderItemRepository extends JpaRepository<OrderItemEntity,Long> {
}
