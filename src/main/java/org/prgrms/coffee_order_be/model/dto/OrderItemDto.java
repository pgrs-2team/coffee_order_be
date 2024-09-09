package org.prgrms.coffee_order_be.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemDto {

    private String category;

    private long price;

    private int quantity;

    private String productName;

}
