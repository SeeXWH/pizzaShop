package com.example.pizzaShop.dto;

import com.example.pizzaShop.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDto {
    private List<OrderItem> itemList;
}
