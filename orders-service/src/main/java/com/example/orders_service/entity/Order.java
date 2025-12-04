package com.example.orders_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data // Lombok tự sinh Getter, Setter, toString...
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String productName;
    private Integer quantity;
    private Double price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // PENDING, CONFIRMED, CANCELLED

    private LocalDateTime orderDate;

    // Tự động gán ngày tạo
    @PrePersist
    protected void onCreate() {
        this.orderDate = LocalDateTime.now();
        if (this.status == null) {
            this.status = OrderStatus.PENDING;
        }
    }
}


