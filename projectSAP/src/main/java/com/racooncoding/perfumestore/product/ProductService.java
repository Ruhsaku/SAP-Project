package com.racooncoding.perfumestore.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import jakarta.transaction.Transactional;
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

    @Transactional
    public void updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getProductId())
                .orElseThrow(() -> new IllegalStateException(
                        "Product with id " + product.getProductId() + " does not exist!"
                ));
        Integer id = product.getProductId();
        String name = product.getProductName();
        ProductType type = product.getProductType();
        Integer quantity = product.getProductQuantity();
        BigDecimal price = product.getProductPrice();
        String description = product.getProductDescription();

        if(id != null && id > 0) {
            existingProduct.setProductId(id);
        } else {
            System.err.println("Error for id!");
        }
        if(name != null) {
            existingProduct.setProductName(name);
        } else {
            System.err.println("Error for name!");
        }
        if(type == ProductType.MEN || type == ProductType.WOMEN) {
            existingProduct.setProductType(type);
        } else {
            System.err.println("Error for type!");
        }
        if(quantity > 0) {
            existingProduct.setProductQuantity(quantity);
        } else {
            System.err.println("Error for quantity!");
        }
        if(price != null &&
                price.compareTo(BigDecimal.ZERO) > 0) {
            existingProduct.setProductPrice(price);
        } else {
            System.err.println("Error for price!");
        }
        if(description != null) {
            existingProduct.setProductDescription(description);
        } else {
            System.err.println("Error for description!");
        }
    }
}
