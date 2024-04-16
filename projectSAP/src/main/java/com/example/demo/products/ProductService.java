package com.example.demo.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;  // Assuming a ProductRepository is injected

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }
}
