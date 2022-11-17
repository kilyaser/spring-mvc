package ru.gb.model;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;


@Data
public class Product {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);
    private final long id;
    private String title;
    private int cost;

    public Product(String title, int cost) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.title = title;
        this.cost = cost;
    }
    public static void decId() {
        ID_GENERATOR.decrementAndGet();
    }
}
