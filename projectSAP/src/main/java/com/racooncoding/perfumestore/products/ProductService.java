package com.racooncoding.perfumestore.products;

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
    public void addNewProduct(Products products) {
        Optional<Products> productOptional = productRepository
                .findProductByProductName(products.getProductName());

        if (productOptional.isPresent()) {
            // TODO email validation
            // A customer entity present with the same email is not an invalid state of the server/db.
            // Use a different exception.
            throw new IllegalStateException("This product exists!");
        } else {
            productRepository.save(products);
        }
    }
}
