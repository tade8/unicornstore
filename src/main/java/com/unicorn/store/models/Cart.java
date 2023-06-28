package com.unicorn.store.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    private List<Product> productList = new ArrayList<>();

}
