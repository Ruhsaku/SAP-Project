package com.racooncoding.perfumestore.products;

import jakarta.persistence.*;

@Entity
@Table
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductType productType;
    private Integer quantity;
    private Double price;
    private String description;

    public Products() {}

    public Products(String productName,
                    ProductType productType,
                    Integer quantity,
                    Double price,
                    String description) {
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
                    Double price,
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
