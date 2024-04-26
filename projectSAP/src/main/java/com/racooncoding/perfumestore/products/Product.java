package com.racooncoding.perfumestore.products;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name =  "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductType productType;
    private Integer quantity;
    @Column(name = "price", precision=10, scale=2, nullable = true)
    private BigDecimal price;
    private String description;

    public Products() {}

    public Products(String description,
                    BigDecimal price,
                    String productName,
                    ProductType productType,
                    Integer quantity) {
        this.productName = productName;
        this.productType = productType;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public Products(Integer productId,
                    String productName,
                    ProductType productType,
                    Integer quantity,
                    BigDecimal price,
                    String description) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productType=" + productType +
                ", quantity=" + quantity +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return Objects.equals(productId, products.productId) && Objects.equals(productName, products.productName) && productType == products.productType && Objects.equals(quantity, products.quantity) && Objects.equals(price, products.price) && Objects.equals(description, products.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productType, quantity, price, description);
    }
}
