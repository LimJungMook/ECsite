package com.mookShopping.mook.web;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {

    private Long id;

    @NotEmpty(message = "상품명을 입력하세요")
    @Size(max = 20, message = "상품명은 최대 20자까지 입력할 수 있습니다.")
    private String name;

    @NotNull(message = "수량을 입력하세요")
    @Min(value = 1)
    private int quantity;

    @NotNull(message = "가격을 입력하세요")
    @Min(value = 1)
    private Long price;
}
