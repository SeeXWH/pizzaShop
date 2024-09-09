package com.example.pizzaShop.service;

import com.example.pizzaShop.dto.ProductsDto;
import com.example.pizzaShop.model.OrderItem;
import com.example.pizzaShop.model.Orders;
import com.example.pizzaShop.repository.AppUserRepository;
import com.example.pizzaShop.repository.OrderRepository;
import com.example.pizzaShop.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AppUserRepository appUserRepository;


    public Orders createOrder(String token, ProductsDto productsDto) {
        List<OrderItem> data = productsDto.getItemList();
        String name = jwtTokenProvider.getUsernameFromJWT(token);
        Long userId = appUserRepository.findIdByName(name);
        Date createdAt = new Date();
        Orders orders = new Orders(userId, "new", createdAt, data);
        orderRepository.save(orders);
        return orders;
    }
}
