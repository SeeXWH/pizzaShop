package com.example.pizzaShop.controller;

import com.example.pizzaShop.dto.AppUserDto;
import com.example.pizzaShop.dto.ProfileDTO;
import com.example.pizzaShop.model.AppUser;
import com.example.pizzaShop.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pizza-api-demo")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;



    @PostMapping("/auth/register")
    public String addUser(@RequestBody AppUser appUser, HttpServletRequest request){
        AppUserDto appUserDto = new AppUserDto(appUser.getEmail(), appUser.getPassword());
        appUserService.register(appUser);
        return appUserService.login(appUserDto, request);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody AppUserDto appUserDto, HttpServletRequest request) {
        return appUserService.login(appUserDto, request);
    }

    @GetMapping("/user/profile")
    public ProfileDTO profile(@RequestHeader String token){
        return appUserService.profile(token);
    }
}
