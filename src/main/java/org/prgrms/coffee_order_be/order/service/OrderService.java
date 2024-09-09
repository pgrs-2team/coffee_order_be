package org.prgrms.coffee_order_be.order.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.common.exception.BusinessLogicException;
import org.prgrms.coffee_order_be.common.exception.ExceptionCode;
import org.prgrms.coffee_order_be.order.dto.OrderCreateDto;
import org.prgrms.coffee_order_be.order.dto.OrderItemCreateDto;
import org.prgrms.coffee_order_be.order.dto.OrderResponseDto;
import org.prgrms.coffee_order_be.order.entity.Order;
import org.prgrms.coffee_order_be.order.entity.OrderItem;
import org.prgrms.coffee_order_be.order.repository.OrderRepository;
import org.prgrms.coffee_order_be.product.entity.Product;
import org.prgrms.coffee_order_be.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;

  @Transactional
  public OrderResponseDto createOrder(OrderCreateDto createDto) {

    List<OrderItem> orderItems = convertToOrderItems(createDto.getOrderItems());

    Order order = createDto.toEntity(orderItems);
    orderRepository.save(order);

    return OrderResponseDto.from(order);
  }

  private List<OrderItem> convertToOrderItems(List<OrderItemCreateDto> orderItemsDto) {
    return orderItemsDto.stream()
        .map(this::createOrderItem)
        .collect(Collectors.toList());
  }

  private OrderItem createOrderItem(OrderItemCreateDto dto) {
    Product product = productRepository.findById(dto.getProductId())
        .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_PRODUCT));

    return dto.toEntity(product);
  }

}
