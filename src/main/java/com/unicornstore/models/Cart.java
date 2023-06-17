package com.unicornstore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cart {
    @Id
    private Long id;
    @OneToMany
    private List<Product> productList;

    public Cart() {}
}
