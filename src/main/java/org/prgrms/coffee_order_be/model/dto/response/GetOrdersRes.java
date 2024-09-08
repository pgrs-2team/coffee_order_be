package org.prgrms.coffee_order_be.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.prgrms.coffee_order_be.model.dto.OrderItemDto;

import java.util.List;

@Getter
@Setter
public class GetOrdersRes {
    List<OrderItemDto> orderItemDtos;

    public GetOrdersRes(List<OrderItemDto> orderItemDtos) {
        this.orderItemDtos = orderItemDtos;
    }
}
