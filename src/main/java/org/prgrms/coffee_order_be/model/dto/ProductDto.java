package org.prgrms.coffee_order_be.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDto {

    private String productName;

    private String category;

    private long price;

    private String description;
}
