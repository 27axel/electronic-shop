package ru.h2o.shop.controller;

import org.springframework.web.bind.annotation.*;
import ru.h2o.shop.entity.Product;
import ru.h2o.shop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.create(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/type/{type}")
    public List<Product> getAllProducts(@PathVariable String type) {
        return productService.findAllByType(type);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }
}
