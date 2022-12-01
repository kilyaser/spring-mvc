package ru.gb.controllers.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.dto.ProductDto;
import ru.gb.model.Product;
import ru.gb.service.ProductService;



@RestController
@RequestMapping("/api/v1/products")
public class RestProductController {
    private final ProductService productService;

    @Autowired
    public RestProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_cost", required = false) Integer minPrice,
            @RequestParam(name = "max_cost", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }

        return productService.find(minPrice, maxPrice, titlePart, page).map(ProductDto::new);
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable(name = "id") Long id) {
        return new ProductDto(productService.getProductById(id));
    }

    @PostMapping
    public Product saveNewProduct(@RequestBody Product product) {
        product.setId(null);
        return productService.addProduct(product);

    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
    }
}
