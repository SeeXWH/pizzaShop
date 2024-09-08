package com.example.pizzaShop.repository;

import com.example.pizzaShop.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
