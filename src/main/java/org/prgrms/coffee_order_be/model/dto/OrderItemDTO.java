package org.prgrms.coffee_order_be.model.dto;

import org.prgrms.coffee_order_be.model.entity.OrderEntity;
import org.prgrms.coffee_order_be.model.entity.OrderItemEntity;
import org.prgrms.coffee_order_be.model.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItemDTO {
        private UUID productId;  // 상품 ID
        private int quantity;    // 수량


    // Entity -> DTO 변환
    public static OrderItemDTO toorderItemDTO(OrderItemEntity orderItemEntity) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.productId = orderItemEntity.getProduct().getProductId();
        dto.quantity = orderItemEntity.getQuantity();
        return dto;
    }

    // DTO -> Entity 변환
    public static OrderItemEntity toEntity(OrderItemDTO dto, ProductEntity product, OrderEntity order) {
        OrderItemEntity entity = new OrderItemEntity();
        entity.setProduct(product);
        entity.setOrder(order);
        entity.setQuantity(dto.getQuantity());
        entity.setCategory(product.getCategory());

        return entity;
    }

        public OrderItemDTO() {
        }

        public UUID getProductId() {
            return productId;
        }

        public void setProductId(UUID productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
