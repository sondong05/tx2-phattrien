package com.example.orders_service.controller;


import com.example.orders_service.entity.Order;
import com.example.orders_service.entity.OrderStatus;
import com.example.orders_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // API 1: Tạo đơn hàng
    // POST: http://localhost:8080/api/orders
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    // API 2: Xem tất cả đơn hàng
    // GET: http://localhost:8080/api/orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // API 3: Xem đơn hàng theo ID
    // GET: http://localhost:8080/api/orders/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // API 4: Cập nhật trạng thái đơn hàng
    // PUT: http://localhost:8080/api/orders/{id}/status?status=CONFIRMED
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        Order updatedOrder = orderService.updateOrderStatus(id, status);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        }
        return ResponseEntity.notFound().build();
    }

    // API 5: Hủy đơn hàng
    // PUT: http://localhost:8080/api/orders/{id}/cancel
    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long id) {
        Order cancelledOrder = orderService.cancelOrder(id);
        if (cancelledOrder != null) {
            return ResponseEntity.ok("Đơn hàng đã được hủy thành công.");
        }
        return ResponseEntity.notFound().build();
    }
}
