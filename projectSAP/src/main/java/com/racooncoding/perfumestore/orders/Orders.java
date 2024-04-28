package com.racooncoding.perfumestore.orders;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private Integer customerId;
    private Integer addressId;
    private Timestamp orderDate;

    public Orders() {
    }

    public Orders(Integer customerId,
                  Integer addressId,
                  Timestamp orderDate) {
        this.customerId = customerId;
        this.addressId = addressId;
        this.orderDate = orderDate;
    }

    public Orders(Integer orderId,
                  Integer customerId,
                  Integer addressId,
                  Timestamp orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.addressId = addressId;
        this.orderDate = orderDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", addressId=" + addressId +
                ", orderDate=" + orderDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(orderId, orders.orderId) &&
                Objects.equals(customerId, orders.customerId) &&
                Objects.equals(addressId, orders.addressId) &&
                Objects.equals(orderDate, orders.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, addressId, orderDate);
    }
}
