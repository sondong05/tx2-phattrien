package com.example.payment_service.service;

import com.example.payment_service.entity.Payment;
import com.example.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // 1. Tạo thanh toán mới
    public Payment createPayment(Payment payment) {
        // Có thể thêm logic gọi sang Order Service để kiểm tra đơn hàng tồn tại hay chưa
        // Nhưng ở mức cơ bản, ta chỉ lưu thông tin thanh toán
        return paymentRepository.save(payment);
    }

    // 2. Lấy thông tin thanh toán theo ID
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    // 3. Lấy lịch sử thanh toán của một đơn hàng
    public List<Payment> getPaymentsByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }

    // 4. Xem tất cả thanh toán (cho Admin)
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}