package org.prgrms.coffee_order_be.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.model.dto.request.CreateOderReq;
import org.prgrms.coffee_order_be.model.entity.OrderItem;
import org.prgrms.coffee_order_be.model.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Order" , description = "Order 관련 API 모음")
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "주문 생성")
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid CreateOderReq req){
        List<OrderItem> resp = orderService.createOrder(req);

        return ResponseEntity.ok(resp);
    }
}
