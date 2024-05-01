package com.racooncoding.perfumestore.product;

import com.racooncoding.perfumestore.exceptions.DeleteProductException;
import com.racooncoding.perfumestore.exceptions.InvalidNewProductDataException;
import com.racooncoding.perfumestore.exceptions.ProductExistsException;
import com.racooncoding.perfumestore.exceptions.UpdateProductErrorException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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
            throw new ProductExistsException();
        }
        if (!product.getProductName().matches("[a-zA-z0-9 ]+")){
            throw new InvalidNewProductDataException("Invalid product name. Try again.");
        }
        productRepository.save(product);
    }

    public void deleteProduct(Integer productId) throws DeleteProductException {
        boolean exists = productRepository.existsById(productId);
        if (!exists) {
            throw new DeleteProductException(
                    "Product with id " + productId + " does not exist!");
        }

        productRepository.deleteById(productId);
    }

    @Transactional
    public void updateProduct(Product product) throws UpdateProductErrorException {
        boolean exists = productRepository.existsById(product.getProductId());
        if (!exists) {
            throw new UpdateProductErrorException(
                    "Product with id " + product.getProductId() + " does not exist!");
        }

//        Optional<Product> existingProduct = productRepository.findById(product.getProductId());
        AtomicReference<Product> existingProduct = new AtomicReference<>(new Product());
        List<Product> products = productRepository.findAll();
        products.forEach(p -> {
            if(Objects.equals(p.getProductId(), product.getProductId())){
                existingProduct.set(product);
            }
        });
        
        Integer id = product.getProductId();
        String name = product.getProductName();
        ProductType type = product.getProductType();
        Integer quantity = product.getProductQuantity();
        BigDecimal price = product.getProductPrice();
        String description = product.getProductDescription();

        if (id != null && id > 0) {
            existingProduct.get().setProductId(id);
        } else {
            throw new UpdateProductErrorException("Id error detected!");
        }

        if (name != null) {
            existingProduct.get().setProductName(name);
        } else {
            throw new UpdateProductErrorException("Name error detected!");
        }


        existingProduct.get().setProductType(type);


        existingProduct.get().setProductQuantity(quantity);




        existingProduct.get().setProductPrice(price);


        existingProduct.get().setProductDescription(description);

    }
}
