package com.example.prduct_service.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data // Lombok tự sinh Getter, Setter, toString...
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;       // Tên sản phẩm

    private Double price;      // Giá

    private Integer quantity;  // Số lượng tồn kho

    private String description; // Mô tả sản phẩm
}
