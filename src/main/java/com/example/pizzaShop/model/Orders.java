package com.example.pizzaShop.model;

import com.example.pizzaShop.dto.ProductsDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String status;
    @CreationTimestamp
    private Date createdAt;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> data;

    public Orders(Long userId, String status, Date createdAt, List<OrderItem> data) {
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
        this.data = data;
    }
}
