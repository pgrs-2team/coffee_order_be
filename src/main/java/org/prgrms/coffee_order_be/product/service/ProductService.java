package org.prgrms.coffee_order_be.product.service;

import static org.prgrms.coffee_order_be.common.exception.ExceptionCode.DUPLICATED_PRODUCT;
import static org.prgrms.coffee_order_be.common.exception.ExceptionCode.NOT_FOUND_PRODUCT;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.common.exception.BusinessLogicException;
import org.prgrms.coffee_order_be.common.exception.ExceptionCode;
import org.prgrms.coffee_order_be.product.dto.ProductCreateDto;
import org.prgrms.coffee_order_be.product.dto.ProductResponseDto;
import org.prgrms.coffee_order_be.product.entity.Product;
import org.prgrms.coffee_order_be.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public ProductResponseDto create(ProductCreateDto createDto) throws BusinessLogicException {
    Optional<Product> existProduct = productRepository.findByProductName(
        createDto.getProductName());

    if (existProduct.isPresent())
      throw new BusinessLogicException(DUPLICATED_PRODUCT);

    Product product = createDto.toEntity();
    productRepository.save(product);
    return ProductResponseDto.from(product);
  }

  public List<ProductResponseDto> getProducts() {
    List<Product> products = productRepository.findAll();

    return products.stream().map(ProductResponseDto::from).toList();
  }

  public ProductResponseDto getProduct(UUID productId) {
    Product findProduct = productRepository.findById(productId).orElseThrow(
        () -> new BusinessLogicException(NOT_FOUND_PRODUCT)
    );
    return ProductResponseDto.from(findProduct);
  }
}
