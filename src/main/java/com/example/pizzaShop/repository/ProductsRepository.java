package com.example.pizzaShop.repository;

import com.example.pizzaShop.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
