package org.prgrms.coffee_order_be.model.service;

import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.model.dto.ProductDto;
import org.prgrms.coffee_order_be.model.dto.request.CreateProductReq;
import org.prgrms.coffee_order_be.model.dto.response.GetProductsRes;
import org.prgrms.coffee_order_be.model.entity.Product;
import org.prgrms.coffee_order_be.model.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto createProduct(CreateProductReq req){
        Product product = req.toProduct();

        productRepository.save(product);

        return product.toDto();
    }

    public List<GetProductsRes> getProducts(){
        List<Product> products = productRepository.findAll();

        return products.stream().map(GetProductsRes::new).toList();
    }

    public ProductDto getProduct(UUID uuid){
        Product product = productRepository.findById(uuid)
                .orElseThrow( () -> new RuntimeException("존재하지 않는 상품입니다."));

        return product.toDto();
    }
    
}
