package com.example.demo.products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Products saveProduct(Products product) {
        return productRepository.save(product);
    }

    public Optional<Products> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }
}
