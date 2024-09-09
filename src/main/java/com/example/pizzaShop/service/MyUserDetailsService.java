package com.example.pizzaShop.service;

import com.example.pizzaShop.model.AppUser;
import com.example.pizzaShop.repository.AppUserRepository;
import com.example.pizzaShop.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.pizzaShop.config.MyUserDetails;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private AppUserRepository repository;
    private JwtTokenProvider jwtTokenProvider;

    public MyUserDetailsService(AppUserRepository repository) {
        this.repository = repository;
    }

    public MyUserDetailsService() {
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = Optional.ofNullable(repository.findByName(username));
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + "Not found"));
    }



}