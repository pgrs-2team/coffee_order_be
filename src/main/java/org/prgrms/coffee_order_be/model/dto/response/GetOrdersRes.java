package org.prgrms.coffee_order_be.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.prgrms.coffee_order_be.model.dto.OrderItemDto;
import org.prgrms.coffee_order_be.model.entity.OrderStatus;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class GetOrdersRes {
    private UUID uuid;

    private List<OrderItemDto> orderRes;

    private OrderStatus orderStatus;

    private String address;

    private String postcode;

    public GetOrdersRes(UUID uuid, List<OrderItemDto> orderRes, OrderStatus orderStatus, String address, String postcode) {
        this.uuid = uuid;
        this.orderRes = orderRes;
        this.orderStatus = orderStatus;
        this.address = address;
        this.postcode = postcode;
    }
}
