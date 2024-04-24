package com.racooncoding.perfumestore.ordersproducts;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name =  "ordersproducts")
public class OrdersProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderProductId;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private BigDecimal totalPrice;

    public OrdersProducts() {}

    public OrdersProducts(Integer orderId,
                          Integer productId,
                          Integer quantity,
                          BigDecimal totalPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public OrdersProducts(Integer orderProductId,
                          Integer orderId,
                          Integer productId,
                          Integer quantity,
                          BigDecimal totalPrice) {
        this.orderProductId = orderProductId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Integer getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Integer orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
