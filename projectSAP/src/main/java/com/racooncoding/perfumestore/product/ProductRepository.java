package com.racooncoding.perfumestore.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Integer> {
    Optional<Product> findProductByProductName(String productName);
    Optional<Product> findProductByProductId(Integer productId);
}
