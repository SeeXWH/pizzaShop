package com.example.pizzaShop.dto;

import com.fasterxml.jackson.dataformat.yaml.util.StringQuotingChecker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
    private String email;
    private String password;
}
