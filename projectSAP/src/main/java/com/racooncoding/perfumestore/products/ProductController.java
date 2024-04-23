package com.racooncoding.perfumestore.products;
import org.springframework.beans.factory.annotation.Autowired;
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
}
