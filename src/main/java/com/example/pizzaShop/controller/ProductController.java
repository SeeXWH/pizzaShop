package com.example.pizzaShop.controller;

import com.example.pizzaShop.model.Products;
import com.example.pizzaShop.service.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizza-api-demo")
@AllArgsConstructor
public class ProductController {
    private final ProductsService productsService;

    @PostMapping("/products/createProduct")
    public Products createProduct(@RequestBody Products products){
        return productsService.createProduct(products);
    }

    @DeleteMapping("/products/deleteProduct")
    public String deleteProduct(@RequestParam("id") Long id){
        return productsService.deleteProduct(id);
    }

    @PutMapping("/products/changeProduct")
    public Products changeProduct(@RequestBody Products products, @RequestParam("id") Long id){
        return productsService.changeProduct(id, products);
    }

    @GetMapping("/products/{id}")
    public Products getProduct(@PathVariable("id") Long id){
        return productsService.getProduct(id);
    }

    @GetMapping("/products")
    public List<Products> getAllProducts(){
        return productsService.getAllProducts();
    }
}
