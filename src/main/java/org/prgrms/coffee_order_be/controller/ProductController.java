package org.prgrms.coffee_order_be.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.model.dto.ProductDto;
import org.prgrms.coffee_order_be.model.dto.request.CreateProductReq;
import org.prgrms.coffee_order_be.model.dto.request.UpdateProductReq;
import org.prgrms.coffee_order_be.model.dto.response.GetProductsRes;
import org.prgrms.coffee_order_be.model.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Product" , description = "Product 관련 API 모음")
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 생성")
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid CreateProductReq req){
        ProductDto resp = productService.createProduct(req);
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "상품 리스트 조회")
    @GetMapping
    public ResponseEntity<List<GetProductsRes>> getProducts(){
        List<GetProductsRes> resp = productService.getProducts();
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "상품 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable(name = "id") UUID uuid){
        ProductDto resp = productService.getProduct(uuid);
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "상품 수정")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(name = "id") UUID uuid,
                                                    @RequestBody @Valid UpdateProductReq req) {
        ProductDto resp = productService.updateProduct(uuid, req);
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "상품 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") UUID uuid){
        String resp = productService.deleteProduct(uuid);
        return ResponseEntity.ok(resp);
    }
}
