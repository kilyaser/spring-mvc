package ru.gb.service;


import org.springframework.stereotype.Service;
import ru.gb.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
    public Product getProductById(Long id) {
        return  products.stream()
                .filter(p -> Objects.equals(id, p.getId()))
                .findFirst()
                .orElse(null);
    }

    @PostConstruct
    public void init() {
        addProduct(new Product("Milk", "10.5"));
        addProduct(new Product("Water", "1.5"));
        addProduct(new Product("Juice", "15.5"));
        addProduct(new Product("Butter", "20.5"));
        addProduct(new Product("Bread", "8.5"));
    }
}
