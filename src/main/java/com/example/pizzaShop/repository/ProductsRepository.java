package com.example.pizzaShop.repository;

import com.example.pizzaShop.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    Products findByName(String name);
}
