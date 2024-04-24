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

    @PostMapping
    public Products createProduct(@RequestBody Products product) {
        return productService.saveProduct(product);
    }

//    @GetMapping("/{id}")
//    public Optional<Products> getProduct(@PathVariable Integer id) {
//        return productService.getProductById(id);
//    }

    @GetMapping(path = "/dashboard/getAllProducts")
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Products updateProduct(@PathVariable Integer id, @RequestBody Products product) {
        product.setProductId(id);
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }

    @PostMapping("/dashboard/addProduct")
    public ResponseEntity<?> addNewProduct(@RequestBody Products products) {
        try {
            productService.addNewProduct(products);
            System.out.println("Product added successfully");
            return ResponseEntity.ok().body("{\"redirectUrl\": \"/dashboard\"}");
        } catch (IllegalStateException e) {
            System.err.println("Product adding failed. Please try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Product adding failed\"}");
        }
    }
}
