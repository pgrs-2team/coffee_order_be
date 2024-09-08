package org.prgrms.coffee_order_be.model.dto;

import org.prgrms.coffee_order_be.model.entity.OrderEntity;
import org.prgrms.coffee_order_be.model.entity.ProductEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderDTO {
    private UUID orderId;
    private String email;
    private String address;
    private String postcode;
    private String orderStatus;
    private List<OrderItemDTO> orderItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // OrderEntity -> OrderDTO 변환
    public static OrderDTO toOrderDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(orderEntity.getOrderId());
        orderDTO.setEmail(orderEntity.getEmail());
        orderDTO.setAddress(orderEntity.getAddress());
        orderDTO.setPostcode(orderEntity.getPostcode());
        orderDTO.setOrderStatus(orderEntity.getOrderStatus());
        orderDTO.setCreatedAt(orderEntity.getCreatedAt());
        orderDTO.setUpdatedAt(orderEntity.getUpdatedAt());
        return orderDTO;
    }

    // OrderDTO -> OrderEntity 변환
    public static OrderEntity toOrderEntity(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderDTO.getOrderId());
        orderEntity.setEmail(orderDTO.getEmail());
        orderEntity.setAddress(orderDTO.getAddress());
        orderEntity.setPostcode(orderDTO.getPostcode());
        orderEntity.setOrderStatus(orderDTO.getOrderStatus());
        orderEntity.setCreatedAt(orderDTO.getCreatedAt());
        orderEntity.setUpdatedAt(orderDTO.getUpdatedAt());
        return orderEntity;
    }


    public OrderDTO() {
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", orderItems=" + orderItems +
                ", orderStatus='" + orderStatus + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }


}
