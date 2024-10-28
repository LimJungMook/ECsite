package com.mookShopping.mook.service;

import com.mookShopping.mook.domain.Cart;
import com.mookShopping.mook.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Long saveCart(Cart cart) {
        return cartRepository.saveCart(cart);
    }

    public void deleteCart(Long cartId) {
        Cart cart = cartRepository.findCartById(cartId);
        cartRepository.deleteCart(cart);
    }

    public List<Cart> findCart(Long memberId) {
        return cartRepository.findCartByMember(memberId);
    }
}
