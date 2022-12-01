package ru.gb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.model.Product;
import ru.gb.service.ProductService;

import java.util.List;


@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public Product getProduct(@PathVariable Long id) {
        return  productService.getProductById(id);
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("productList", productService.getProducts());
        return "products";
    }

    //вызов формы для добавления
    @GetMapping("/add-product")
    public String addProductForm(Product product) {
        return "add-product";
    }

    //добавление продукта
    @PostMapping("/add-product")
    public String addProduct(Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/remove-product/{id}")
    public String removeProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
    @GetMapping("/products-filter")
    @ResponseBody
    public Page<Product> getProductsBetween(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_cost", required = false) Integer min,
            @RequestParam(name = "max_cost", required = false) Integer max,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(min, max, titlePart, page);
    }

}
