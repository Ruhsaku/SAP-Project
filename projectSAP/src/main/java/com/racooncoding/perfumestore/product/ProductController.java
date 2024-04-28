package com.racooncoding.perfumestore.product;

import com.racooncoding.perfumestore.response.Response;
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
        // TODO --> Exception Handling
        return productService.getAllProducts();
    }

    @PostMapping(path = "/dashboard/addProduct")
    public ResponseEntity<Response> addNewProduct(@RequestBody Product product) {
        // TODO --> Exception Handling
        Response response;
        try {
            productService.addNewProduct(product);
            response = new Response("Product added successfully", "/dashboard");
            System.out.println(response.getMessage());
            return ResponseEntity.ok().body(response);
        } catch (IllegalStateException e) {
            response = new Response("Product adding failed. Please try again.");
            System.err.println(response.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @DeleteMapping(path = "/dashboard/removeProduct")
    public ResponseEntity<Response> removeProduct(@RequestBody Product product) {
        // TODO --> Exception Handling
        Response response;
        try {
            productService.deleteProduct(product.getProductId());
            response = new Response("Product deleted successfully", "/dashboard");
            System.out.println(response.getMessage());
            return ResponseEntity.ok().body(response);

        } catch (IllegalStateException e) {
            response = new Response("Product's deleting failed!");
            System.err.println(response.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PutMapping(path = "/dashboard/updateProduct")
    public ResponseEntity<Response> updateProduct(@RequestBody Product product) {
        // TODO --> Exception Handling
        Response response;
        try {
            productService.updateProduct(product);
            response = new Response("Product updated successfully", "/dashboard");
            System.out.println(response.getMessage());
            return ResponseEntity.ok().body(response);
        } catch (IllegalStateException e) {
            response = new Response("Product's updating failed!");
            System.err.println(response.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }
}
