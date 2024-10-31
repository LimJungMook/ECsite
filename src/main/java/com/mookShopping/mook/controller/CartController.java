package com.mookShopping.mook.controller;

import com.mookShopping.mook.domain.Cart;
import com.mookShopping.mook.domain.CartItem;
import com.mookShopping.mook.domain.Member;
import com.mookShopping.mook.domain.Product;
import com.mookShopping.mook.service.CartService;
import com.mookShopping.mook.web.CartAjax;
import com.mookShopping.mook.web.CartForm;
import com.mookShopping.mook.web.ProductForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/cart/form")
    public String cartForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        Cart cart = cartService.findCart(loginMember.getId());
        List<Integer> quantities = IntStream.rangeClosed(1, 99)
                .boxed()
                .collect(Collectors.toList());
        model.addAttribute("quantities", quantities);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/cart/new")
    public String newCart(@Valid ProductForm productForm, BindingResult bindingResult, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        cartService.saveCart(loginMember.getId(), productForm.getId(), productForm.getQuantity());
        return "redirect:/";
    }


    @PostMapping("/cart/updateQuantity")
    @ResponseBody // AJAX 요청에 대한 JSON 응답을 처리합니다.
    public ResponseEntity<String> updateCartQuantity(
            @RequestBody CartAjax cartAjax
            , HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute("loginMember");
        Long cartItemIdL = Long.valueOf(cartAjax.getCartItemId());
        Integer quantityL = Integer.valueOf(cartAjax.getQuantity());
        cartService.updateQuantity(cartItemIdL, quantityL, loginMember.getId());
        System.out.println("quantity" + quantityL);


        return ResponseEntity.ok("수량이 성공적으로 업데이트되었습니다.");
    }

    @ResponseBody
    @PostMapping("/cart/delete")
    public ResponseEntity<String> deleteCartQuantity(@RequestBody CartAjax cartAjax,
                                                     HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute("loginMember");
        cartService.deleteCart(loginMember.getId(),Long.valueOf(cartAjax.getCartItemId()));
        return ResponseEntity.ok("삭제되었습니다.");

    }
}
