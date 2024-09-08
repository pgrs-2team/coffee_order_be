package org.prgrms.coffee_order_be.Controller;

import org.prgrms.coffee_order_be.model.dto.OrderDTO;
import org.prgrms.coffee_order_be.model.entity.OrderEntity;
import org.prgrms.coffee_order_be.model.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping // 주문 생성기능
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrder(orderDTO); // 수정된 부분
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping //이메일로 주문 조회기능
    public ResponseEntity<List<OrderDTO>> getOrdersByEmail(@RequestParam String email) {
        List<OrderDTO> orders = orderService.getOrdersByEmail(email);
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/{orderId}") // 식별 값으로 단건 주문을 조회
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable UUID orderId) {
        Optional<OrderEntity> orderEntity = Optional.ofNullable(orderService.getOrderById(orderId));
        OrderDTO dtos = OrderDTO.toOrderDTO(orderEntity.orElse(null));
        return  ResponseEntity.ok().body(dtos);
    }

    @PutMapping("/{orderId}") // 주문 식별값으로 주문을 수정기능
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable UUID orderId, @RequestBody OrderDTO orderDTO) {
        OrderEntity updatedOrder = orderService.updateOrder(orderId, orderDTO.getAddress(), orderDTO.getPostcode());
        OrderDTO dtos = OrderDTO.toOrderDTO(updatedOrder);
        return  ResponseEntity.ok().body(dtos);
    }

    @DeleteMapping("/{orderId}") // 주문 삭제기능
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}