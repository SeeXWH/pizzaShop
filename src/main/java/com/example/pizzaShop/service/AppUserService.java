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

    public String register(AppUser appUser, HttpServletRequest request) {
        if (appUserRepository.findByName(appUser.getEmail()) != null) {
            String errorMessage = "a user with that Email already exists";
            throw new PizzaShopExeption(errorMessage);
        }
        String newPassword = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(newPassword);
        appUserRepository.save(appUser);
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(appUser.getEmail(), newPassword));
//        String token = jwtTokenProvider.generateToken(authentication);
//        request.getSession().setAttribute("token", token);
//        return "access_token: " + token;
        return "1";
    }

    public String login(AppUserDto appUserDto, HttpServletRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(appUserDto.getEmail(), appUserDto.getPassword()));
            String token = jwtTokenProvider.generateToken(authentication);
            request.getSession().setAttribute("token", token);
            return "access_token: " + token;
        } catch (PizzaShopExeption e) {
            throw new PizzaShopExeption("bad authentication");
        }
    }
}
