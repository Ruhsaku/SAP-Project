package com.racooncoding.perfumestore.product;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
        // TODO --> Exception Handling
        Optional<Product> productOptional = productRepository
                .findProductByProductName(product.getProductName());

        if (productOptional.isPresent()) {
            throw new IllegalStateException("Product with that name already exists!");
        } else {
            productRepository.save(product);
        }
    }

    public void deleteProduct(Integer productId) {
        // TODO --> Exception Handling
        boolean exists = productRepository.existsById(productId);
        if (!exists) {
            throw new IllegalStateException(
                    "Product with id " + productId + " does not exist!");
        }
        productRepository.deleteById(productId);
    }

    @Transactional
    public void updateProduct(Product product) {
        // TODO --> Exception Handling
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

        try {
            if (id != null && id > 0) {
                existingProduct.setProductId(id);
            } else {
                throw new IllegalArgumentException("Error for id!");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        try {
            if (name != null) {
                existingProduct.setProductName(name);
            } else {
                throw new IllegalArgumentException("Error for name!");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        try {
            if (type == ProductType.MEN || type == ProductType.WOMEN) {
                existingProduct.setProductType(type);
            } else {
                throw new IllegalArgumentException("Error for type!");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        try {
            if (quantity > 0) {
                existingProduct.setProductQuantity(quantity);
            } else {
                throw new IllegalArgumentException("Error for quantity!");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        try {
            if (price != null && price.compareTo(BigDecimal.ZERO) > 0) {
                existingProduct.setProductPrice(price);
            } else {
                throw new IllegalArgumentException("Error for price!");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        try {
            if (description != null) {
                existingProduct.setProductDescription(description);
            } else {
                throw new IllegalArgumentException("Error for description!");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
