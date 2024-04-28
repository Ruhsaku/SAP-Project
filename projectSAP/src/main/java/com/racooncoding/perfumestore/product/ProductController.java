package com.racooncoding.perfumestore.product;

import com.racooncoding.perfumestore.exceptions.DeleteProductException;
import com.racooncoding.perfumestore.exceptions.ProductsListEmptyException;
import com.racooncoding.perfumestore.exceptions.UpdateProductErrorException;
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
    public List<Product> getAllProducts() throws ProductsListEmptyException {

        if (productService.getAllProducts().isEmpty()){
            throw new ProductsListEmptyException();
        }
        return productService.getAllProducts();
    }

    @PostMapping(path = "/dashboard/addProduct")
    public ResponseEntity<Response> addNewProduct(@RequestBody Product product){
    // TODO --> Fix Exception Handling
        try{
            Response response;
            productService.addNewProduct(product);
            response = new Response("Product added successfully", "/dashboard");
            System.out.println(response.getMessage());
            return ResponseEntity.ok().body(response);
        }catch (RuntimeException e){
            Response response;
            response = new Response("Invalid product data. Try again.", "/dashboard");
            System.err.println(response.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }


    }

    @DeleteMapping(path = "/dashboard/removeProduct")
    public ResponseEntity<Response> removeProduct(@RequestBody Product product) throws DeleteProductException {
        Response response;
        productService.deleteProduct(product.getProductId());
        response = new Response("Product deleted successfully", "/dashboard");
        System.out.println(response.getMessage());
        return ResponseEntity.ok().body(response);

    }

    @PutMapping(path = "/dashboard/updateProduct")
    public ResponseEntity<Response> updateProduct(@RequestBody Product product) throws UpdateProductErrorException {
        // TODO --> Fix Exception Handling
        Response response;
        productService.updateProduct(product);
        response = new Response("Product updated successfully", "/dashboard");
        System.out.println(response.getMessage());
        return ResponseEntity.ok().body(response);
    }
}
