package org.prgrms.coffee_order_be.model.service;

import org.prgrms.coffee_order_be.model.dto.OrderDTO;
import org.prgrms.coffee_order_be.model.entity.OrderEntity;
import org.prgrms.coffee_order_be.model.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    OrderItemService orderItemService;


    // 주문 생성
    public OrderDTO createOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = OrderDTO.toOrderEntity(orderDTO);
        orderDTO.setOrderStatus("주문 완료");
        orderDTO.setCreatedAt(LocalDateTime.now());
        orderDTO.setUpdatedAt(LocalDateTime.now());
        OrderEntity savedOrder = orderRepository.save(orderEntity);

        orderItemService.createOrderItems(orderDTO.getOrderItems(), savedOrder);


        return OrderDTO.toOrderDTO(savedOrder);

    }

    // 주문 조회 (이메일로)
    public List<OrderDTO> getOrdersByEmail(String email) {
        Optional<OrderEntity> orders = orderRepository.findByEmail(email);
        return orders.stream().map(OrderDTO::toOrderDTO).collect(Collectors.toList());
    }

    // 주문 조회 (id로 단건)
    public OrderEntity getOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
    }


    // 주문 수정 (address, postcode)
    public OrderEntity updateOrder(UUID orderId, String address, String postcode) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
        orderEntity.setAddress(address);
        orderEntity.setPostcode(postcode);
        orderEntity.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(orderEntity);
    }

    // 주문 삭제 (주문 완료에서만)
    public void deleteOrder(UUID orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
        if ("주문 완료".equals(orderEntity.getOrderStatus())) {
            orderRepository.delete(orderEntity);
        } else {
            throw new RuntimeException("주문 완료 상태만 삭제가 가능!!!!!!!");
        }
    }
}
