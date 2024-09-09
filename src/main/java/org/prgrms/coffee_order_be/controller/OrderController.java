package org.prgrms.coffee_order_be.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.model.dto.request.CreateOderReq;
import org.prgrms.coffee_order_be.model.dto.request.UpdateOrderReq;
import org.prgrms.coffee_order_be.model.dto.response.GetOrdersRes;
import org.prgrms.coffee_order_be.model.entity.Order;
import org.prgrms.coffee_order_be.model.entity.OrderItem;
import org.prgrms.coffee_order_be.model.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Order" , description = "Order 관련 API 모음")
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "주문 생성")
    @PostMapping
    public ResponseEntity<List<OrderItem>> createOrder(@RequestBody @Valid CreateOderReq req){
        List<OrderItem> resp = orderService.createOrder(req);

        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "이메일로 주문 조회")
    @GetMapping
    public ResponseEntity<List<GetOrdersRes>> getOrder(@RequestParam("email") String email){
        List<GetOrdersRes> resp = orderService.getOrder(email);
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "주문 수정")
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") UUID uuid, @RequestBody @Valid UpdateOrderReq req){
        Order resp = orderService.updateOrder(uuid, req);
        return ResponseEntity.ok(resp);
    }

}
