package org.prgrms.coffee_order_be.model.service;

import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.model.dto.OrderItemsDto;
import org.prgrms.coffee_order_be.model.dto.request.CreateOderReq;
import org.prgrms.coffee_order_be.model.entity.Order;
import org.prgrms.coffee_order_be.model.entity.OrderItem;
import org.prgrms.coffee_order_be.model.entity.Product;
import org.prgrms.coffee_order_be.model.repository.OrderItemRepository;
import org.prgrms.coffee_order_be.model.repository.OrderRepository;
import org.prgrms.coffee_order_be.model.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public List<OrderItem> createOrder(CreateOderReq req){
        Order order = req.toOrder();
        List<OrderItem> orderItems = new ArrayList<>();

        for(OrderItemsDto orderItem : req.getOrderItems()){
            Product product = productRepository.findById(orderItem.getProductsUUID())
                    .orElseThrow(() -> new RuntimeException("상품이 존재하지 않습니다."));

            orderItems.add(OrderItem.builder()
                    .product(product)
                    .order(order)
                    .quantity(orderItem.getQuantity())
                    .category(product.getCategory())
                    .price(product.getPrice() * orderItem.getQuantity())
                    .build());
        }

        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        return orderItems;
    }
}
