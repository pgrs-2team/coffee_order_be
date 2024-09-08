package org.prgrms.coffee_order_be.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.product.service.ProductService;
import org.prgrms.coffee_order_be.product.dto.ProductCreateDto;
import org.prgrms.coffee_order_be.product.dto.ProductResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductCreateDto createDto) {
    ProductResponseDto responseDto = productService.create(createDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }
}
