package com.racooncoding.perfumestore.products;

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

    @PostMapping("/dashboard/addProduct")
    public ResponseEntity<?> addNewProduct(@RequestBody Product product) {
        try {
            productService.addNewProduct(product);
            System.out.println("Product added successfully");
            return ResponseEntity.ok().body("{\"redirectUrl\": \"/dashboard\"}");
        } catch (IllegalStateException e) {
            System.err.println("Product adding failed. Please try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Product adding failed\"}");
        }
    }
}
