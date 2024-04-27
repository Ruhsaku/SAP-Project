package com.racooncoding.perfumestore.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/dashboard/getAllProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping(path = "/dashboard/addProduct")
    public ResponseEntity<?> addNewProduct(@RequestBody Product product) {
        try {
            productService.addNewProduct(product);
            System.out.println("Product added successfully");
            return ResponseEntity.ok().body("{\"redirectUrl\": \"/dashboard\"}");
        } catch (IllegalStateException e) {
            System.err.println("Product adding failed. Please try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Product adding failed!\"}");
        }
    }

    @DeleteMapping(path = "/dashboard/removeProduct")
    public ResponseEntity<?> removeProduct(@RequestBody Product product) {
        try {
            productService.deleteProduct(product.getProductId());
            System.out.println("Product deleted successfully");
            return ResponseEntity.ok().body("{\"redirectUrl\": \"/dashboard\"}");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Product's deleting failed!\"}");
        }
    }

    @PutMapping(path = "/dashboard/updateProduct")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        try {
            productService.updateProduct(product);
            System.out.println("Product updated successfully");
            return ResponseEntity.ok().body("{\"redirectUrl\": \"/dashboard\"}");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Product's updating failed!\"}");
        }
    }
}
