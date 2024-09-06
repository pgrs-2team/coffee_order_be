package org.prgrms.coffee_order_be.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.model.dto.ProductDto;
import org.prgrms.coffee_order_be.model.dto.request.CreateProductReq;
import org.prgrms.coffee_order_be.model.dto.response.GetProductsRes;
import org.prgrms.coffee_order_be.model.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid CreateProductReq req){
        ProductDto resp = productService.createProduct(req);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    public ResponseEntity<List<GetProductsRes>> getProducts(){
        List<GetProductsRes> resp = productService.getProducts();
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable(name = "id") UUID uuid){
        ProductDto resp = productService.getProduct(uuid);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") UUID uuid){
        String resp = productService.deleteProduct(uuid);
        return ResponseEntity.ok(resp);
    }
}
