package org.prgrms.coffee_order_be.order.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

  @DisplayName("Order를_생성할_수_있다")
  @Test
  void Order를_생성할_수_있다() {
    // given
    String email = "test@gamil.com";
    String address = "testAddress";
    String postcode = "postcodeTest";
    OrderStatus orderStatus = OrderStatus.ORDER_COMPLETED;
    OrderItem orderItemOne = OrderItem.builder().build();
    OrderItem orderItemTwo = OrderItem.builder().build();
    List<OrderItem> orderItems = List.of(orderItemOne, orderItemTwo);

    // when
    Order order = Order.builder()
        .email(email)
        .address(address)
        .postcode(postcode)
        .orderStatus(orderStatus)
        .orderItems(orderItems.toArray(OrderItem[]::new))
        .build();

    // then
    assertThat(order.getOrderId()).isNull();
    assertThat(order.getEmail()).isEqualTo(email);
    assertThat(order.getAddress()).isEqualTo(address);
    assertThat(order.getPostcode()).isEqualTo(postcode);
    assertThat(order.getOrderStatus()).isEqualTo(orderStatus);
    assertThat(order.getOrderItems()).hasSize(2);
    assertThat(order.getOrderItems().get(0).getOrder()).isEqualTo(order);
    assertThat(order.getOrderItems().get(1).getOrder()).isEqualTo(order);
  }
}