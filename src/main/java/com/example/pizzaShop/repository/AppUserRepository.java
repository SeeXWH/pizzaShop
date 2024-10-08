package com.example.pizzaShop.repository;

import com.example.pizzaShop.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppUserRepository  extends JpaRepository<AppUser, Long> {
    AppUser findByName(String userName);
    AppUser findByEmail(String email);
    @Query("SELECT u.id FROM AppUser u WHERE u.name = ?1")
    Long findIdByName(String name);
}
