package com.example.prduct_service.repository;


import com.example.prduct_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository đã hỗ trợ sẵn các hàm CRUD cơ bản
}
