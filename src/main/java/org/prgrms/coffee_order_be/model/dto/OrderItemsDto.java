package org.prgrms.coffee_order_be.model.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class OrderItemsDto {

    private UUID productsUUID;

    private int quantity;
}
