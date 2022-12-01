package ru.gb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @GetMapping("/products-between/{min}/{max}")
    @ResponseBody
    public List<Product> getProductsBetween(
            @PathVariable("min") int min,
            @PathVariable("max") int max) {
        return productService.fineByCostBetween(min, max);
    }
    @GetMapping("/products-min/{min}")
    @ResponseBody
    public List<Product> getProductsGranderThen(@PathVariable("min") int min) {
        return productService.findByCostGraterThan(min);
    }
    @GetMapping("/products-max/{max}")
    @ResponseBody
    public List<Product> getProductLessThan(@PathVariable("max") int max) {
        return productService.findByCostLessThan(max);
    }



}
