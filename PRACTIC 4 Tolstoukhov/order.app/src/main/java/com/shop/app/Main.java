package com.shop.app;

import com.github.javafaker.Faker;
import com.shop.model.Clothing;
import com.shop.model.Electronics;
import com.shop.model.Product;
import com.shop.processing.OrderProcessor;
import com.shop.storage.OrderRepository;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        OrderRepository repository = new OrderRepository();
        fillRepository(repository);

        OrderProcessor<Product> generalProcessor = new OrderProcessor<>();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        repository.getAll().forEach(System.out::println);

        System.out.println("Обробляю дані у 3 потоки...");
        repository.getAll().forEach(product -> executor.submit(() -> generalProcessor.processOrder(product)));

        shutdownExecutor(executor);
    }

    private static void fillRepository(OrderRepository repo) {
        Faker faker = new Faker();

        for (int i = 0; i < 5; i++) {
            repo.add(Electronics.builder()
                    .id(UUID.randomUUID().toString())
                    .name(faker.commerce().productName())
                    .price(Double.parseDouble(faker.commerce().price().replace(",", ".")))
                    .warrantyPeriodMonths(faker.number().numberBetween(12, 36))
                    .build());
        }

        for (int i = 0; i < 5; i++) {
            repo.add(Clothing.builder()
                    .id(UUID.randomUUID().toString())
                    .name(faker.commerce().productName())
                    .price(Double.parseDouble(faker.commerce().price().replace(",", ".")))
                    .size(faker.options().option("S", "M", "L", "XL"))
                    .material(faker.commerce().material())
                    .build());
        }
    }

    private static void shutdownExecutor(ExecutorService executor) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}