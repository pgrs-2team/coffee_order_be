package org.prgrms.coffee_order_be.product.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.prgrms.coffee_order_be.product.dto.ProductUpdateDto;
import org.prgrms.coffee_order_be.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  private Product product;

  @BeforeEach
  void setup() {
    product = Product.builder()
        .productName("Latte")
        .category("Coffee")
        .price(5000L)
        .description("Delicious Latte")
        .build();
    productRepository.save(product);
  }

  @DisplayName("제품을_생성할_수_있다")
  @Test
  void 제품을_생성할_수_있다() {
    // given
    Product productOne = Product.builder()
        .productName("Colombia")
        .category("coffee beans")
        .price(1000L)
        .description("test")
        .build();
    productRepository.save(productOne);

    // when
    Optional<Product> findProduct = productRepository.findById(productOne.getProductId());

    // then
    assertThat(findProduct.isPresent()).isTrue();
    assertThat(findProduct.get().getProductId()).isEqualTo(productOne.getProductId());
  }

  @DisplayName("제품리스트를_조회할_수_있다")
  @Test
  void 제품리스트를_조회할_수_있다() {
    // given

    // when
    List<Product> products = productRepository.findAll();

    // then
    assertThat(products).hasSize(1);
    assertThat(products.get(0).getProductId()).isEqualTo(product.getProductId());
  }

  @DisplayName("식별값으로_제품을_조회할_수_있다")
  @Test
  void 식별값으로_제품을_조회할_수_있다() {
    // given
    Optional<Product> findProduct = productRepository.findById(product.getProductId());
    Optional<Product> unknownProduct = productRepository.findById(UUID.randomUUID());

    // when // then
    assertThat(findProduct.isEmpty()).isFalse();
    assertThat(findProduct.get().getProductId()).isEqualTo(product.getProductId());

    assertThat(unknownProduct.isEmpty()).isTrue();
  }

  @DisplayName("제품명으로_제품을_조회할_수_있다")
  @Test
  void 제품명으로_제품을_조회할_수_있다() {
    // given
    Optional<Product> findProduct = productRepository.findByProductName(product.getProductName());
    Optional<Product> unknownProduct = productRepository.findByProductName("unknown");

    // when // then
    assertThat(findProduct.isEmpty()).isFalse();
    assertThat(findProduct.get().getProductId()).isEqualTo(product.getProductId());
    assertThat(findProduct.get().getProductName()).isEqualTo(product.getProductName());

    assertThat(unknownProduct.isEmpty()).isTrue();
  }

  @DisplayName("제품을_업데이트할_수_있다")
  @Test
  void 제품을_업데이트할_수_있다() {
    // given
    Product savedProduct = productRepository.findById(product.getProductId()).get();
    ProductUpdateDto updateDto = new ProductUpdateDto(6000L, "Updated Description");

    // when
    savedProduct.updateFromDto(updateDto);

    // then
    Product updatedProduct = productRepository.findById(product.getProductId()).get();
    assertThat(updatedProduct.getDescription()).isEqualTo(updateDto.getDescription());
    assertThat(updatedProduct.getPrice()).isEqualTo(updateDto.getPrice());
  }

  @DisplayName("식별값으로_제품을_삭제할_수_있다")
  @Test
  void 식별값으로_제품을_삭제할_수_있다() {
    // given
    Product savedProduct = productRepository.findById(product.getProductId()).get();

    // when
    productRepository.delete(savedProduct);
    Optional<Product> findProduct = productRepository.findById(product.getProductId());

    // then
    assertThat(findProduct.isEmpty()).isTrue();
  }

}