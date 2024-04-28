package com.racooncoding.perfumestore.cart;

import com.racooncoding.perfumestore.ordersproducts.OrdersProducts;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Long id;

//    @OneToMany(mappedBy = "cart")
    private List<OrdersProducts> cartItems = new ArrayList<>();
    // Other attributes and getters/setters

    // TODO --> Cart class for checkout
}

