package com.racooncoding.perfumestore.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository
        extends JpaRepository<Products, Integer> {
    Optional<Products> findProductByProductName(String productName);
}
