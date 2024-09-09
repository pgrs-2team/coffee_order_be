package org.prgrms.coffee_order_be.order.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.prgrms.coffee_order_be.product.entity.Product;

class OrderItemTest {

  @DisplayName("OrderItem을_생성할_수_있다")
  @Test
  void OrderItem을_생성할_수_있다() {
    // given
    Product product = Product.builder().build();
    String category = "categoryTest";
    Long price = 1000L;
    Integer quantity = 100;

    // when
    OrderItem orderItem = OrderItem.builder()
        .product(product)
        .category(category)
        .price(price)
        .quantity(quantity)
        .build();

    // then
    assertThat(orderItem.getSeq()).isNull();
    assertThat(orderItem.getOrder()).isNull();
    assertThat(orderItem.getProduct()).isEqualTo(product);
    assertThat(orderItem.getCategory()).isEqualTo(category);
    assertThat(orderItem.getPrice()).isEqualTo(price);
    assertThat(orderItem.getQuantity()).isEqualTo(quantity);

  }

}