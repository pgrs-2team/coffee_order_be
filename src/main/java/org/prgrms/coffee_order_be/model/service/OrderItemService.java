package org.prgrms.coffee_order_be.model.service;

import org.prgrms.coffee_order_be.model.dto.OrderItemDTO;
import org.prgrms.coffee_order_be.model.entity.OrderEntity;
import org.prgrms.coffee_order_be.model.entity.OrderItemEntity;
import org.prgrms.coffee_order_be.model.entity.ProductEntity;
import org.prgrms.coffee_order_be.model.repository.OrderItemRepository;
import org.prgrms.coffee_order_be.model.repository.OrderRepository;
import org.prgrms.coffee_order_be.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;


    public void createOrderItems(List<OrderItemDTO> orderItems, OrderEntity orderEntity) {
        for (OrderItemDTO itemDTO : orderItems) {
            ProductEntity productEntity = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("해당 ID의 상품을 찾을 수 없습니다."));

            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setProduct(productEntity);
            orderItem.setCategory(productEntity.getCategory());
            orderItem.setPrice(productEntity.getPrice());
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setOrder(orderEntity);
            orderItem.setCreatedAt(LocalDateTime.now());
            orderItem.setUpdatedAt(LocalDateTime.now());

            orderItemRepository.save(orderItem);
        }
    }
}