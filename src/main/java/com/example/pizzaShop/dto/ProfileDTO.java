package com.example.pizzaShop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileDTO {
    private Long id;
    private String email;
    private String passwordHash;
    private String name;

}
