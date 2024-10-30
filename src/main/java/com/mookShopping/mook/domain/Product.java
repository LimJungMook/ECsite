package com.mookShopping.mook.domain;

import com.mookShopping.mook.exception.OutOfStockException;
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

    private String filePath;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProductList = new ArrayList<>();


    public void remove(int quantity) {
        int restQuantity = this.quantity - quantity;
        if (restQuantity < 0) {
            throw new OutOfStockException("상품의 재고가 부족합니다. 현재 재고 수량: " + restQuantity);
        }
        this.quantity = restQuantity;
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
