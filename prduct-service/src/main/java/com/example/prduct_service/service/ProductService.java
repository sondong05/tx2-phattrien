package com.example.prduct_service.service;


import com.example.prduct_service.entity.Product;
import com.example.prduct_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // 1. Thêm sản phẩm
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // 2. Xem tất cả sản phẩm
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 3. Xem sản phẩm theo ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // 4. Sửa sản phẩm
    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setQuantity(productDetails.getQuantity());
            product.setDescription(productDetails.getDescription());
            return productRepository.save(product);
        }).orElse(null); // Trả về null nếu không tìm thấy ID
    }

    // 5. Xóa sản phẩm theo ID
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
