package org.prgrms.coffee_order_be.product.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.prgrms.coffee_order_be.product.dto.ProductUpdateDto;

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

  @DisplayName("ProductUpdateDto로_Product를_수정할_수_있다")
  @Test
  void ProductUpdateDto로_Product를_수정할_수_있다() {
    // given
    String productName = "coffeeBean";
    String category = "coffee bean";
    Long price = 1000L;
    String description = "test";

    Product product = Product.builder()
        .productName(productName)
        .category(category)
        .price(price)
        .description(description)
        .build();

    ProductUpdateDto updateDto = new ProductUpdateDto(1L, "update");

    // when
    product.updateFromDto(updateDto);

    // then
    assertThat(product.getPrice()).isEqualTo(updateDto.getPrice());
    assertThat(product.getDescription()).isEqualTo(updateDto.getDescription());
  }
}