package org.prgrms.coffee_order_be.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "orders")
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id")
    private UUID id;

    private String email;

    private String address;

    private String postcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public Order(String email, String address, String postcode) {
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = OrderStatus.ORDER_COMPLETED;
    }

    public Order update(String address, String postcode){
        this.address = address;
        this.postcode = postcode;

        return this;
    }
}
