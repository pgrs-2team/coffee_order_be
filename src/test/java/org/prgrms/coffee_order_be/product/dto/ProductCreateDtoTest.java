package org.prgrms.coffee_order_be.product.dto;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.prgrms.coffee_order_be.product.entity.Product;

public class ProductCreateDtoTest {

  private Validator validator;

  @BeforeEach
  void setUp() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @DisplayName("ProductCreateDto를_생성할_수_있다")
  @Test
  void ProductCreateDto를_생성할_수_있다() {
    // Given
    String productName = "CoffeeBean";
    String category = "Coffee bean";
    Long price = 1000L;
    String description = "test";

    // When
    ProductCreateDto dto = new ProductCreateDto(productName, category, price, description);

    // Then
    assertThat(dto.getProductName()).isEqualTo(productName);
    assertThat(dto.getCategory()).isEqualTo(category);
    assertThat(dto.getPrice()).isEqualTo(price);
    assertThat(dto.getDescription()).isEqualTo(description);

  }

  @DisplayName("ProductCreateDto으로_Product를_생성할_수_있다")
  @Test
  void ProductCreateDto으로_Product를_생성할_수_있다() {
    // Given
    String productName = "CoffeeBean";
    String category = "Coffee bean";
    Long price = 1000L;
    String description = "test";

    ProductCreateDto dto = new ProductCreateDto(productName, category, price, description);

    // When
    Product product = dto.toEntity();

    // Then
    assertThat(product).isNotNull();
    assertThat(product.getProductId()).isNull();
    assertThat(product.getProductName()).isEqualTo(productName);
    assertThat(product.getCategory()).isEqualTo(category);
    assertThat(product.getPrice()).isEqualTo(price);
    assertThat(product.getDescription()).isEqualTo(description);
    assertThat(product.getCreatedAt()).isNull();
    assertThat(product.getUpdatedAt()).isNull();
  }

  @DisplayName("ProductCreateDto을_생성할_때_Valid가_동작한다")
  @Test
  void ProductCreateDto을_생성할_때_Valid가_동작한다() {
    // Given
    ProductCreateDto dto = new ProductCreateDto("", "", -1L, "");

    // When
    Set<ConstraintViolation<ProductCreateDto>> violations = validator.validate(dto);

    // Then
    assertThat(violations).isNotEmpty();
    assertThat(violations.stream()
        .anyMatch(v -> v.getPropertyPath().toString().equals("productName"))).isTrue();
    assertThat(violations.stream()
        .anyMatch(v -> v.getPropertyPath().toString().equals("category"))).isTrue();
    assertThat(
        violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("price"))).isTrue();
    assertThat(violations.stream()
        .anyMatch(v -> v.getPropertyPath().toString().equals("description"))).isTrue();

  }
}
