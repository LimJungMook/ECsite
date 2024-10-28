package com.mookShopping.mook.web;

import com.mookShopping.mook.domain.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderForm {
    private ProductForm product;

    private Long totalPrice;

}
