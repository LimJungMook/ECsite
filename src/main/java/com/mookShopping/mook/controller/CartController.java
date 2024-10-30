package com.mookShopping.mook.controller;

import com.mookShopping.mook.domain.Cart;
import com.mookShopping.mook.domain.Member;
import com.mookShopping.mook.service.CartService;
import com.mookShopping.mook.web.CartForm;
import com.mookShopping.mook.web.ProductForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/cart/form")
    public String cartForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member loginMember = (Member)session.getAttribute("loginMember");
        List<Cart> cart = cartService.findCart(loginMember.getId());
        model.addAttribute("cart", cart);
        return "cart";
    }
}
