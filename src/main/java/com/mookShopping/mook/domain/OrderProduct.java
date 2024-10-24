package com.mookShopping.mook.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "OrderProduct")
public class OrderProduct {

    @Id
    @Column(name = "order_product_id")
    @GeneratedValue
    private Long id;

    private int quantity;

    private Long orderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public static OrderProduct createProduct(Product product, Long orderPrice, int quantity) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setOrderPrice(orderPrice);
        orderProduct.setQuantity(quantity);

        product.remove(quantity);
        return orderProduct;
    }


    public void cancel() {
        product.addStock(quantity);
    }
}
