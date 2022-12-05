package ru.gb.controllers.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.converters.ProductConverter;
import ru.gb.dto.ProductDto;
import ru.gb.exceptions.ResourceNotFoundException;
import ru.gb.model.Cart;
import ru.gb.model.Product;
import ru.gb.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/purchases")
@RequiredArgsConstructor
public class RestCartController {
    private final Cart cart;
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public List<ProductDto> getAllProductsFromCart() {
        return cart.getAllProductFromCart();
    }
    @GetMapping("/{id}")
    public ProductDto addProductToCartById(@PathVariable("id") Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id:" + id));
        ProductDto productDto = productConverter.entityToDto(product);
        cart.addProduct(productDto);
        return productDto;
    }
    @DeleteMapping("/{id}")
    public void deleteProductFromCartById(@PathVariable(name = "id") Long id) {
        cart.deleteProductFromCartById(id);
    }


}
