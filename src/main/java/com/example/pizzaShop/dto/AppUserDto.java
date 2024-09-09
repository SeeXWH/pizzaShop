package com.example.pizzaShop.dto;

import com.fasterxml.jackson.dataformat.yaml.util.StringQuotingChecker;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppUserDto {
    private String email;
    private String password;
}
