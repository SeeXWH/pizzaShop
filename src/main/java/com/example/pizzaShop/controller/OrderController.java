package com.example.pizzaShop.controller;

import com.example.pizzaShop.dto.ProductsDto;
import com.example.pizzaShop.model.Orders;
import com.example.pizzaShop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pizza-api-demo")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public Orders createOrder(@RequestHeader String token, @RequestBody ProductsDto productsDto){
        return orderService.createOrder(token, productsDto);
    }
}
