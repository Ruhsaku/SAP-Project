package com.racooncoding.perfumestore.products;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }
    public void addNewProduct(Product product) {
        Optional<Product> productOptional = productRepository
                .findProductByProductName(product.getProductName());

        if (productOptional.isPresent()) {
            throw new IllegalStateException("This product exists!");
        } else {
            productRepository.save(product);
        }
    }
}
