package ru.gb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.model.Product;
import ru.gb.repository.ProductRepository;
import ru.gb.repository.specifications.ProductSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> find(Integer minCost, Integer maxCost, String partTitle, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minCost != null) {
            spec.and(ProductSpecification.priceGranderOrEqualsThen(minCost));
        }
        if (maxCost != null) {
            spec.and(ProductSpecification.priceLessOrEqualsThen(maxCost));
        }
        if (partTitle != null) {
            spec.and(ProductSpecification.titleLike(partTitle));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
//    public List<Product> fineByCostBetween(int min, int max) {
//        return productRepository.findByCostBetween(min, max);
//    }
//    public List<Product> findByCostGraterThan(int min) {
//        return productRepository.findByCostGreaterThan(min);
//    }
//    public List<Product> findByCostLessThan(int max) {
//        return productRepository.findByCostLessThan(max);
//    }

}

