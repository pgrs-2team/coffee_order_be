package org.prgrms.coffee_order_be.model.repository;

import org.prgrms.coffee_order_be.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
}
