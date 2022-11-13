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


@Controller
//@RequestMapping("/products")
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
        Product.decId();
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


}
