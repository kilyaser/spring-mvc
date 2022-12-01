package ru.gb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.model.Product;
import ru.gb.repository.ProductRepository;

import java.util.List;


@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
    public List<Product> fineByCostBetween(int min, int max) {
        return productRepository.findByCostBetween(min, max);
    }
    public List<Product> findByCostGraterThan(int min) {
        return productRepository.findByCostGreaterThan(min);
    }
    public List<Product> findByCostLessThan(int max) {
        return productRepository.findByCostLessThan(max);
    }

}

