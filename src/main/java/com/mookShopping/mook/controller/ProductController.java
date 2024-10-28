package com.mookShopping.mook.controller;

import com.mookShopping.mook.domain.Product;
import com.mookShopping.mook.service.ProductService;
import com.mookShopping.mook.web.ProductForm;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/new")
    public String newProductForm(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "productRegi";
    }

    @PostMapping("/product/new")
    public String saveProduct(@Valid ProductForm productForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "productRegi";
        }

        Product product = new Product();
        product.setName(productForm.getName());
        product.setQuantity(productForm.getQuantity());
        product.setPrice(productForm.getPrice());
        Long saveProduct = productService.saveProduct(product);
        log.info("saveProduct: " + saveProduct);
        return "redirect:/product/productList";
    }

    @GetMapping("/product/productList")
    public String getList(Model model) {
        List<Product> productList = productService.findAllProduct();
        model.addAttribute("product",productList );
        return "productList";
    }


    @GetMapping("/product/detail/{productId}")
    public String getDetail(Model model, @PathVariable Long productId) {
        Product selectedProduct = productService.findOneProduct(productId);
        model.addAttribute("product", selectedProduct);
        return "productDetail";
    }

    @GetMapping("/product/modify")
    public String modifyForm(Model model) {
        return "productModify";
    }


}
