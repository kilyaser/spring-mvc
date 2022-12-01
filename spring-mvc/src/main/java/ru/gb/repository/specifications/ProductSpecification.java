package ru.gb.repository.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.gb.model.Product;

public class ProductSpecification {

    public static Specification<Product> priceGranderOrEqualsThen(Integer cost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<Product> priceLessOrEqualsThen(Integer cost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<Product> titleLike(String titlePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%S%%", titlePart));
    }
}
