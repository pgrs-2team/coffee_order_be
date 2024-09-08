package org.prgrms.coffee_order_be.product.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.prgrms.coffee_order_be.common.exception.BusinessLogicException;
import org.prgrms.coffee_order_be.common.exception.ExceptionCode;
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
    ProductCreateDto dto = new ProductCreateDto(
        productName, "Coffee", 1000L, "Test Description");
    Product existingProduct = Product.builder().build();

    when(productRepository.findByProductName(productName)).thenReturn(Optional.of(existingProduct));

    // When // Then
    assertThatThrownBy(() -> productService.create(dto)).isInstanceOf(BusinessLogicException.class)
        .hasMessage(ExceptionCode.DUPLICATED_PRODUCT.getMessage());
  }

  @DisplayName("제품목록을_조회할_수_있다")
  @Test
  void 제품목록을_조회할_수_있다() {
    // Given
    Product product1 = Product.builder().build();
    Product product2 = Product.builder().build();

    List<Product> productList = Arrays.asList(product1, product2);
    when(productRepository.findAll()).thenReturn(productList);

    // When
    List<ProductResponseDto> responseDtoList = productService.getProducts();

    // Then
    assertThat(responseDtoList).hasSize(2);
  }

  @DisplayName("식별값으로_제품을_조회할_수_있다")
  @Test
  void 식별값으로_제품을_조회할_수_있다() {
    // Given
    UUID productId = UUID.randomUUID();
    Product product = new Product(
        productId, "1", "1", 1L, "1");
    when(productRepository.findById(productId)).thenReturn(Optional.of(product));

    // When
    ProductResponseDto responseDto = productService.getProduct(productId);

    // Then
    assertThat(responseDto).isNotNull();
    assertThat(responseDto.getProductId()).isEqualTo(productId);
  }

  @DisplayName("존재하지_않는_제품을_조회할때_예외가_발생한다")
  @Test
  void 존재하지_않는_제품을_조회할때_예외가_발생한다() {
    // Given
    UUID productId = UUID.randomUUID();
    when(productRepository.findById(productId)).thenReturn(Optional.empty());

    // When // Then
    assertThatThrownBy(() -> productService.getProduct(productId))
        .isInstanceOf(BusinessLogicException.class)
        .hasMessage(ExceptionCode.NOT_FOUND_PRODUCT.getMessage());
  }
}