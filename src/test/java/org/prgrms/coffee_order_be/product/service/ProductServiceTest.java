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
import org.prgrms.coffee_order_be.product.dto.ProductUpdateDto;
import org.prgrms.coffee_order_be.product.entity.Product;
import org.prgrms.coffee_order_be.product.repository.ProductRepository;

class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private ProductService productService;

  private Product product;
  private ProductCreateDto createDto;
  private ProductUpdateDto updateDto;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    product = new Product(UUID.randomUUID(), "test", "test", 1000L,
        "test");
    createDto = new ProductCreateDto("create", "create", 1L,
        "create");
    updateDto = new ProductUpdateDto(2L, "update");
  }

  @DisplayName("제품을_생성할_수_있다")
  @Test
  void 제품을_생성할_수_있다() {
    // Given
    Product product = createDto.toEntity();

    when(productRepository.findByProductName(createDto.getProductName()))
        .thenReturn(Optional.empty());
    when(productRepository.save(product)).thenReturn(product);

    // When
    ProductResponseDto responseDto = productService.create(createDto);

    // Then
    assertThat(responseDto).isNotNull();
    assertThat(responseDto.getProductName()).isEqualTo(createDto.getProductName());
    assertThat(responseDto.getCategory()).isEqualTo(createDto.getProductName());
    assertThat(responseDto.getPrice()).isEqualTo(createDto.getPrice());
    assertThat(responseDto.getDescription()).isEqualTo(createDto.getDescription());
  }

  @DisplayName("중복된_제품명으로_생성할_경우_예외가_발생한다")
  @Test
  void 중복된_제품명으로_생성할_경우_예외가_발생한다() {
    // Given
    Product existingProduct = Product.builder().build();

    when(productRepository.findByProductName(createDto.getProductName()))
        .thenReturn(Optional.of(existingProduct));

    // When // Then
    assertThatThrownBy(() -> productService.create(createDto))
        .isInstanceOf(BusinessLogicException.class)
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
    when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));

    // When
    ProductResponseDto responseDto = productService.getProduct(product.getProductId());

    // Then
    assertThat(responseDto).isNotNull();
    assertThat(responseDto.getProductId()).isEqualTo(product.getProductId());
  }

  @DisplayName("존재하지_않는_제품을_조회할때_예외가_발생한다")
  @Test
  void 존재하지_않는_제품을_조회할때_예외가_발생한다() {
    // Given
    UUID unknownId = UUID.randomUUID();
    when(productRepository.findById(unknownId)).thenReturn(Optional.empty());

    // When // Then
    assertThatThrownBy(() -> productService.getProduct(product.getProductId()))
        .isInstanceOf(BusinessLogicException.class)
        .hasMessage(ExceptionCode.NOT_FOUND_PRODUCT.getMessage());
  }

  @DisplayName("제품을_업데이트할_수_있다")
  @Test
  void 제품을_업데이트할_수_있다() {
    // Given
    when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));

    // When
    ProductResponseDto responseDto = productService.updateProduct(product.getProductId(), updateDto);

    // Then
    assertThat(responseDto).isNotNull();
    assertThat(responseDto.getPrice()).isEqualTo(updateDto.getPrice());
    assertThat(responseDto.getDescription()).isEqualTo(updateDto.getDescription());
  }

  @DisplayName("존재하지않는_제품을_업데이트할_경우_예외가_발생한다")
  @Test
  void 존재하지않는_제품을_업데이트할_경우_예외가_발생한다() {
    // Given
    UUID unknownId = UUID.randomUUID();
    when(productRepository.findById(unknownId)).thenReturn(Optional.empty());

    // When
    assertThatThrownBy(() -> productService.updateProduct(unknownId, updateDto))
        .isInstanceOf(BusinessLogicException.class)
        .hasMessage(ExceptionCode.NOT_FOUND_PRODUCT.getMessage());
  }

  @DisplayName("잘못된_ProductUpdateDto로_제품을_업데이트할_경우_예외가_발생한다")
  @Test
  void 잘못된_ProductUpdateDto로_제품을_업데이트할_경우_예외가_발생한다() {
    // Given
    UUID unknownId = UUID.randomUUID();
    when(productRepository.findById(unknownId)).thenReturn(Optional.empty());

    // When
    assertThatThrownBy(() -> productService.updateProduct(unknownId, updateDto))
        .isInstanceOf(BusinessLogicException.class)
        .hasMessage(ExceptionCode.NOT_FOUND_PRODUCT.getMessage());
  }
}