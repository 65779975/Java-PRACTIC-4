package com.shop.storage;

import com.shop.model.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderRepository {
    private final List<Product> orders = Collections.synchronizedList(new ArrayList<>());

    public void add(Product product) {
        orders.add(product);
    }

    public List<Product> getAll() {
        return new ArrayList<>(orders);
    }
}