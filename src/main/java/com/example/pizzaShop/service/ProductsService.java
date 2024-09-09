package com.example.pizzaShop.service;

import com.example.pizzaShop.exeption.PizzaShopExeption;
import com.example.pizzaShop.model.Products;
import com.example.pizzaShop.repository.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;

    public Products createProduct(Products products){
        if (productsRepository.findByName(products.getName()) != null){
            throw new PizzaShopExeption("product already exists");
        }
        return productsRepository.save(products);
    }

    public String deleteProduct(Long id){
        if (productsRepository.findById(id).isEmpty()){
            throw new PizzaShopExeption("there is no such product");
        }
        productsRepository.deleteById(id);
        return "successful delete";
    }

    public Products changeProduct(Long id, Products products){
        if (productsRepository.findById(id).isEmpty()){
            throw new PizzaShopExeption("there is no such product");
        }
        return productsRepository.save(products);
    }

    public Products getProduct(Long id){
        if (productsRepository.findById(id).isEmpty()){
            throw new PizzaShopExeption("there is no such product");
        }
        return productsRepository.findById(id).orElseThrow();
    }

    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    }
}
