package com.racooncoding.perfumestore.product;

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
    public void addNewProduct(Product product) {
        Optional<Product> productOptional = productRepository
                .findProductByProductName(product.getProductName());

        if (productOptional.isPresent()) {
            throw new IllegalStateException("This product exists!");
        } else {
            productRepository.save(product);
        }
    }
    public void deleteProduct(Integer productId) {
        boolean exists = productRepository.existsById(productId);

        if (!exists) {
            throw new IllegalStateException(
                    "Product with id " + productId + " does not exist!");
        }
        productRepository.deleteById(productId);
    }
}
