package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCostBetween(int min, int max);
    List<Product> findByCostGreaterThan(int min);
    List<Product> findByCostLessThan(int max);
}
