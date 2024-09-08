package org.prgrms.coffee_order_be.product.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

  @DisplayName("Product를_생성할_수_있다")
  @Test
  void Product를_생성할_수_있다() {
    // given
    String productName = "coffeeBean";
    String category = "coffee bean";
    Long price = 1000L;
    String description = "test";

    // when
    Product newProduct = Product.builder()
        .productName(productName)
        .category(category)
        .price(price)
        .description(description)
        .build();

    // then
    assertThat(newProduct.getProductId()).isNull();
    assertThat(newProduct.getCategory()).isEqualTo(category);
    assertThat(newProduct.getPrice()).isEqualTo(price);
    assertThat(newProduct.getDescription()).isEqualTo(description);
    assertThat(newProduct.getCreatedAt()).isNull();
    assertThat(newProduct.getUpdatedAt()).isNull();
  }
}