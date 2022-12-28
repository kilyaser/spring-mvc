package ru.gb.controllers.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.converters.ProductConverter;
import ru.gb.dto.ProductDto;
import ru.gb.exceptions.ResourceNotFoundException;
import ru.gb.model.Product;
import ru.gb.service.ProductService;
import ru.gb.validators.ProductValidator;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class RestProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(minPrice, maxPrice, titlePart, page).map(
                productConverter::entityToDto);

    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable(name = "id") Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found id: " + id));
        return productConverter.entityToDto(p);
    }

    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.save(product);
        product.setId(null);
        return productConverter.entityToDto(product);
    }
    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
    }
}
