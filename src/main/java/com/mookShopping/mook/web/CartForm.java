package com.mookShopping.mook.web;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartForm {

    private Long id;

    private String name;

    private int quantity;

    private Long price;
}
