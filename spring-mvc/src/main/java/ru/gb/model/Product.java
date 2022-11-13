package ru.gb.model;

import lombok.Data;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;


@Data
public class Product {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);
    private final long id;
    private String title;
    private String cost;

    public Product(String title, String cost) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.title = title;
        this.cost = cost;
    }
    public static void decId() {
        ID_GENERATOR.decrementAndGet();
    }
}
