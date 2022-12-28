package ru.gb.model;

import org.springframework.stereotype.Component;
import ru.gb.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private List<ProductDto> addedProducts = new ArrayList<>();

    public void addProduct(ProductDto productDto) {
        addedProducts.add(productDto);
    }
    public List<ProductDto> getAllProductFromCart() {
        return addedProducts;
    }

    public void deleteProductFromCartById(Long id) {
        addedProducts.removeIf(productDto -> productDto.getId().equals(id));
    }
}
