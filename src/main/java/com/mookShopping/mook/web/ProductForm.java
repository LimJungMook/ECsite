package com.mookShopping.mook.web;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {

    private Long id;

    @NotEmpty
    @Max(value = 20)
    private String name;
    @NotEmpty(message = "수량을 입력하세요")
    @Min(value = 1)
    private Long quantity;
    @NotEmpty(message = "가격을 입력하세요")
    @Min(value = 1)
    private Long price;
}
