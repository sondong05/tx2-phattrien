package com.example.orders_service.service;


import com.example.orders_service.entity.Order;
import com.example.orders_service.entity.OrderStatus;
import com.example.orders_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // 1. Tạo đơn hàng
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // 2. Xem tất cả đơn hàng
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 3. Xem đơn hàng theo ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // 4. Cập nhật trạng thái đơn hàng
    public Order updateOrderStatus(Long id, OrderStatus newStatus) {
        return orderRepository.findById(id).map(order -> {
            order.setStatus(newStatus);
            return orderRepository.save(order);
        }).orElse(null); // Hoặc ném Exception nếu không tìm thấy
    }

    // 5. Hủy đơn hàng (Logic: Chuyển trạng thái sang CANCELLED chứ không xóa hẳn khỏi DB)
    public Order cancelOrder(Long id) {
        return updateOrderStatus(id, OrderStatus.CANCELLED);
    }
}
