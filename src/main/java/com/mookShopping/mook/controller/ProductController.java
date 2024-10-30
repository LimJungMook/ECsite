package com.mookShopping.mook.controller;

import com.mookShopping.mook.domain.Product;
import com.mookShopping.mook.service.ProductService;
import com.mookShopping.mook.web.ProductForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Value("${file.dir}")
    private String filePath;

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

        if (!productForm.getImageFile().isEmpty()) {
            //https://velog.io/@seokjun0915/Spring-Boot-%ED%8C%8C%EC%9D%BC-%EC%97%85%EB%A1%9C%EB%93%9C%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C
            try {
                String originalFilename = productForm.getImageFile().getOriginalFilename();
                String uploadDir = filePath;
                File file = new File(uploadDir + originalFilename);
                productForm.getImageFile().transferTo(file);
                product.setFilePath(file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                bindingResult.reject("fileUploadError", "이미지 파일 저장에 실패했습니다.");
                return "productRegi";
            }

        }

        Long saveProduct = productService.saveProduct(product);
        log.info("saveProduct: " + saveProduct);
        return "redirect:/product/productList";
    }

    @GetMapping("/product/productList")
    public String getList(Model model) {
        List<Product> productList = productService.findAllProduct();
        model.addAttribute("product", productList);
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

    @GetMapping("/product/orderProductDetail/{productId}")
    public String getOrderProductDetail(Model model, @PathVariable Long productId) {
        Product selectedProduct = productService.findOneProduct(productId);
        selectedProduct.setQuantity(0);
        model.addAttribute("product", selectedProduct);
        return "orderProductDetail";
    }
}
