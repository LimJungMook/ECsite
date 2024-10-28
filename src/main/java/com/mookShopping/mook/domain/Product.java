package com.mookShopping.mook.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String name;

    private int quantity;

    private Long price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProductList = new ArrayList<>();


    public void remove(int quantity) {
        this.quantity -= quantity;
    }

    public void addStock(int quantity) {
        this.quantity += quantity;
    }

//    CREATE TABLE products(
//            product_id INT PRIMARY KEY,
//            name VARCHAR(255),
//    quantity int,
//    price int
//);
}
