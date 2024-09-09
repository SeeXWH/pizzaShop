package com.example.pizzaShop.controller;

import com.example.pizzaShop.dto.AppUserDto;
import com.example.pizzaShop.model.AppUser;
import com.example.pizzaShop.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pizza-api-demo")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;


    @PostMapping("/auth/register")
    public String addUser(@RequestBody AppUser appUser, HttpServletRequest request){
        System.out.println(appUserService.register(appUser).toString());
        return appUserService.login(appUserService.register(appUser), request);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody AppUserDto appUserDto, HttpServletRequest request) {
        return appUserService.login(appUserDto, request);
    }

    @GetMapping("/test")
    public String test(){
        return "пися попа";
    }
}
