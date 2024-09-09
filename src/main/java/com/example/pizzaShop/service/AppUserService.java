package com.example.pizzaShop.service;

import com.example.pizzaShop.dto.AppUserDto;
import com.example.pizzaShop.dto.ProfileDTO;
import com.example.pizzaShop.exeption.PizzaShopExeption;
import com.example.pizzaShop.model.AppUser;
import com.example.pizzaShop.repository.AppUserRepository;
import com.example.pizzaShop.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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


    public void register(AppUser appUser) {
        if (appUserRepository.findByName(appUser.getName()) != null || appUserRepository.findByEmail(appUser.getEmail()) != null) {
            throw new PizzaShopExeption("a user with that name or email already exists");
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
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

    public ProfileDTO profile(String token){
        String name = jwtTokenProvider.getUsernameFromJWT(token);
        AppUser appUser = appUserRepository.findByName(name);
        return new ProfileDTO(appUser.getId(), appUser.getEmail(), appUser.getPassword(), appUser.getName());
    }
}
