package org.prgrms.coffee_order_be.product.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.prgrms.coffee_order_be.common.excepiton.BusinessLogicException;
import org.prgrms.coffee_order_be.common.excepiton.ExceptionCode;
import org.prgrms.coffee_order_be.product.dto.ProductCreateDto;
import org.prgrms.coffee_order_be.product.dto.ProductResponseDto;
import org.prgrms.coffee_order_be.product.entity.Product;
import org.prgrms.coffee_order_be.product.repository.ProductRepository;

class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private ProductService productService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @DisplayName("제품을_생성할_수_있다")
  @Test
  void 제품을_생성할_수_있다() {
    // Given
    String productName = "CoffeeBean";
    String category = "Coffee";
    Long price = 1000L;
    String description = "Test Description";

    ProductCreateDto dto = new ProductCreateDto(productName, category, price, description);
    Product product = dto.toEntity();

    when(productRepository.findByProductName(productName)).thenReturn(Optional.empty());
    when(productRepository.save(product)).thenReturn(product);

    // When
    ProductResponseDto responseDto = productService.create(dto);

    // Then
    assertThat(responseDto).isNotNull();
    assertThat(responseDto.getProductName()).isEqualTo(productName);
    assertThat(responseDto.getCategory()).isEqualTo(category);
    assertThat(responseDto.getPrice()).isEqualTo(price);
    assertThat(responseDto.getDescription()).isEqualTo(description);
  }

  @DisplayName("중복된_제품명으로_생성할_경우_예외가_발생한다")
  @Test
  void 중복된_제품명으로_생성할_경우_예외가_발생한다() {
    // Given
    String productName = "CoffeeBean";
    ProductCreateDto dto = new ProductCreateDto(productName, "Coffee", 1000L, "Test Description");
    Product existingProduct = Product.builder().build(); // Mock existing product with the same name

    when(productRepository.findByProductName(productName)).thenReturn(Optional.of(existingProduct));

    // When & Then
    assertThatThrownBy(() -> productService.create(dto))
        .isInstanceOf(BusinessLogicException.class)
        .hasMessage(ExceptionCode.DUPLICATED_PRODUCT.getMessage());
  }
}