package org.prgrms.coffee_order_be.model.dto.request;

import lombok.Getter;

import java.util.UUID;

@Getter
public class OrderProductDto {

    private UUID productsUUID;

    private int quantity;
}
