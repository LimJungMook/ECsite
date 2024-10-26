package com.mookShopping.mook.controller;

import com.mookShopping.mook.domain.Product;
import com.mookShopping.mook.web.ProductForm;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ProductController {

    @GetMapping("/product/new")
    public String newProductForm(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "productRegi";
    }

    @GetMapping("/product/detail")
    public String getDetail(Model model) {
        model.addAttribute("product", new ProductForm());
        return "detail";
    }

    @GetMapping("/product/modify")
    public String modifyForm(Model model) {
        return "productModify";
    }

    @PostMapping("/product/new")
    public String saveProduct(@Valid ProductForm productForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "productRegi";
        }

        return "main";
    }
}
