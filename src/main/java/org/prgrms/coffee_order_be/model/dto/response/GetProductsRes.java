package org.prgrms.coffee_order_be.model.dto.response;

import lombok.Getter;
import org.prgrms.coffee_order_be.model.entity.Product;

import java.util.UUID;

@Getter
public class GetProductsRes {
    private final UUID id;

    private final String ProductName;

    public GetProductsRes(Product product){
        this.id = product.getId();
        this.ProductName = product.getProductName();
    }
}
