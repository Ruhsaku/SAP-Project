package com.racooncoding.perfumestore;

import com.racooncoding.perfumestore.exceptions.DeleteProductException;
import com.racooncoding.perfumestore.exceptions.InvalidNewProductDataException;
import com.racooncoding.perfumestore.exceptions.ProductExistsException;
import com.racooncoding.perfumestore.exceptions.UpdateProductErrorException;
import com.racooncoding.perfumestore.product.PerfumeType;
import com.racooncoding.perfumestore.product.Product;
import com.racooncoding.perfumestore.product.ProductRepository;
import com.racooncoding.perfumestore.product.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testGetAllProducts() {
        // Mock data
        List<Product> productList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productList);

        // Test getAllProducts
        assertEquals(productList, productService.getAllProducts());
    }

    @Test
    void testAddNewProduct_Successful() {
        // Mock data
        Product product = new Product("Description", BigDecimal.valueOf(50.0), "Test Product", PerfumeType.MEN, 10, "image_url");
        when(productRepository.findProductByProductName("Test Product")).thenReturn(Optional.empty());

        // Test addNewProduct
        assertDoesNotThrow(() -> productService.addNewProduct(product));
    }

    @Test
    void testAddNewProduct_ProductExistsException() {
        // Mock data
        Product product = new Product("Description", BigDecimal.valueOf(50.0), "Test Product", PerfumeType.MEN, 10, "image_url");
        when(productRepository.findProductByProductName("Test Product")).thenReturn(Optional.of(product));

        // Test addNewProduct with existing product name
        assertThrows(ProductExistsException.class, () -> productService.addNewProduct(product));
    }

    @Test
    void testAddNewProduct_InvalidNewProductDataException() {
        // Mock data
        Product product = new Product("Description", BigDecimal.valueOf(50.0), "InvalidProduct$#", PerfumeType.MEN, 10, "image_url");

        // Test addNewProduct with invalid product name
        assertThrows(InvalidNewProductDataException.class, () -> productService.addNewProduct(product));
    }

    @Test
    void testDeleteProduct_Successful() {
        // Mock data
        when(productRepository.existsById(1)).thenReturn(true);

        // Test deleteProduct
        assertDoesNotThrow(() -> productService.deleteProduct(1));
    }

    @Test
    void testDeleteProduct_DeleteProductException() {
        // Mock data
        when(productRepository.existsById(1)).thenReturn(false);

        // Test deleteProduct with non-existing product
        assertThrows(DeleteProductException.class, () -> productService.deleteProduct(1));
    }

    @Test
    void testUpdateProduct_Successful() {
        // Mock data
        Product product = new Product(1, "Test Product", PerfumeType.MEN, 10, BigDecimal.valueOf(50.0),"Description","image_url");
        when(productRepository.existsById(1)).thenReturn(true);

        // Test updateProduct
        assertDoesNotThrow(() -> productService.updateProduct(product));
    }

    @Test
    void testUpdateProduct_UpdateProductErrorException_IdError() {
        // Mock data
        Product product = new Product("Description", BigDecimal.valueOf(50.0), "Test Product", PerfumeType.MEN, 10, "image_url");

        // Test updateProduct with null id
        assertThrows(UpdateProductErrorException.class, () -> productService.updateProduct(product));
    }

    @Test
    void testUpdateProduct_UpdateProductErrorException_NameError() {
        // Mock data
        Product product = new Product("Description", BigDecimal.valueOf(50.0), "Test Product", PerfumeType.MEN, 10, "image_url");

        // Test updateProduct with null name
        assertThrows(UpdateProductErrorException.class, () -> productService.updateProduct(product));
    }
}

