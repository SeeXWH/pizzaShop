package com.example.pizzaShop.service;

import com.example.pizzaShop.dto.AppUserDto;
import com.example.pizzaShop.exeption.PizzaShopExeption;
import com.example.pizzaShop.model.AppUser;
import com.example.pizzaShop.repository.AppUserRepository;
import com.example.pizzaShop.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserDto register(AppUser appUser) {
        if (appUserRepository.findByName(appUser.getEmail()) != null) {
            String errorMessage = "a user with that Email already exists";
            throw new PizzaShopExeption(errorMessage);
        }
            String newPassword = passwordEncoder.encode(appUser.getPassword());
            appUser.setPassword(newPassword);
            appUserRepository.save(appUser);
            return new AppUserDto(appUser.getEmail(), newPassword);
    }

    public String login(AppUserDto appUserDto, HttpServletRequest request) {
        try {
            System.out.println(1);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(appUserDto.getEmail(), appUserDto.getPassword()));
            System.out.println(2);
            String token = jwtTokenProvider.generateToken(authentication);
            System.out.println(3);
            request.getSession().setAttribute("token", token);
            return "access_token: " + token;
        } catch (PizzaShopExeption e) {
            throw new PizzaShopExeption("bad authentication");
        }
    }
}
