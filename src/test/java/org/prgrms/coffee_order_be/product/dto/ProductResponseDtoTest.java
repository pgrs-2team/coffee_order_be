package org.prgrms.coffee_order_be.product.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.prgrms.coffee_order_be.product.entity.Product;

class ProductResponseDtoTest {

  @DisplayName("Product로_ProductResponseDto을_생성할_수_있다")
  @Test
  void Product로_ProductResponseDto을_생성할_수_있다() {
    // given
    String productName = "CoffeeBean";
    String category = "Coffee bean";
    Long price = 1000L;
    String description = "test";

    Product product = Product.builder()
        .productName(productName)
        .category(category)
        .price(price)
        .description(description)
        .build();

    // when
    ProductResponseDto responseDto = ProductResponseDto.from(product);

    // then
    assertThat(product.getProductId()).isNull();
    assertThat(product.getProductName()).isEqualTo(productName);
    assertThat(product.getCategory()).isEqualTo(category);
    assertThat(product.getPrice()).isEqualTo(price);
    assertThat(product.getDescription()).isEqualTo(description);
    assertThat(product.getCreatedAt()).isNull();
    assertThat(product.getUpdatedAt()).isNull();
  }

}