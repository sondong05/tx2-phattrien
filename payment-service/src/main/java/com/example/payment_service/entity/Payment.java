package com.example.payment_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId; // ID của đơn hàng được thanh toán

    private Double amount; // Số tiền thanh toán

    @Column(name = "payment_method")
    private String paymentMethod; // VD: CASH, BANKING, CREDIT_CARD

    @Column(name = "payment_status")
    private String status; // VD: SUCCESS, FAILED, PENDING

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @PrePersist
    protected void onCreate() {
        this.paymentDate = LocalDateTime.now();
        if (this.status == null) {
            this.status = "PENDING";
        }
    }
}