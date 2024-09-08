package org.prgrms.coffee_order_be.product.dto;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductUpdateDtoTest {

  private Validator validator;

  @BeforeEach
  void setUp() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @DisplayName("ProductUpdateDto를_생성할_수_있다")
  @Test
  void ProductUpdateDto를_생성할_수_있다() {
    // given
    Long price = 1L;
    String description = "update";

    // when
    ProductUpdateDto updateDto = new ProductUpdateDto(price, description);

    // then
    assertThat(updateDto.getPrice()).isEqualTo(price);
    assertThat(updateDto.getDescription()).isEqualTo(description);
  }

  @DisplayName("ProductUpdateDto을_생성할_때_Valid가_동작한다")
  @Test
  void ProductUpdateDto을_생성할_때_Valid가_동작한다() {
    // Given
    ProductUpdateDto inValidDto = new ProductUpdateDto(-1L, "");

    // When
    Set<ConstraintViolation<ProductUpdateDto>> violations = validator.validate(inValidDto);

    // Then
    assertThat(violations).isNotEmpty();
    assertThat(
        violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("price"))).isTrue();
    assertThat(violations.stream()
        .anyMatch(v -> v.getPropertyPath().toString().equals("description"))).isTrue();

  }
}