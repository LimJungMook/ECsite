package com.mookShopping.mook.controller;

import com.mookShopping.mook.domain.Product;
import com.mookShopping.mook.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class HomeController {
    @Autowired
    private ProductService productService;
    @GetMapping("/")
    public String home(Model model) {
        List<Product> productList = productService.findAllProduct();
        model.addAttribute("productList", productList);
        log.info("home controller");
        return "main";
    }
}
