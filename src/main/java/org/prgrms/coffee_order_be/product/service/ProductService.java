package org.prgrms.coffee_order_be.product.service;

import java.util.Optional;
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
      throw new BusinessLogicException(ExceptionCode.DUPLICATED_PRODUCT);

    Product product = createDto.toEntity();
    productRepository.save(product);
    return ProductResponseDto.from(product);
  }
}
