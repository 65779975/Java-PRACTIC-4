package com.shop.processing;

import com.shop.model.Product;

public class OrderProcessor<T extends Product> {
    public void processOrder(T product) {
        String threadName = Thread.currentThread().getName();
        System.out.printf("[%s] Обробляю замовлення: %s (Ціна: %.2f грн)%n", threadName, product.getName(), product.getPrice());
        try {
            // Simulate order processing time.
            Thread.sleep(500L);
        } catch (final InterruptedException e) {
            System.err.printf("Order processing was interrupted: %s%n", e.getMessage());
        }
        System.out.printf("[%s] Обробка продукту %s закінчена! %n", threadName, product.getName());
    }


}