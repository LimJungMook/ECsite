package com.mookShopping.mook.service;

import com.mookShopping.mook.domain.Cart;
import com.mookShopping.mook.domain.CartItem;
import com.mookShopping.mook.domain.Member;
import com.mookShopping.mook.domain.Product;
import com.mookShopping.mook.repository.CartRepository;
import com.mookShopping.mook.repository.MemberRepository;
import com.mookShopping.mook.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public Long saveCart(Long memberId, Long productId, int quantity) {
        Member member = memberRepository.findOne(memberId);
        Product product = productRepository.findOneProduct(productId);

        // 기존 Cart가 있는지 확인
        Cart cart = cartRepository.findCartByMember(memberId);
        if (cart == null) {
            CartItem cartItem = CartItem.addCartItem(product, quantity);
            cart = Cart.addCart(member, cartItem); // 새 Cart 생성
            return cartRepository.saveCart(cart); // 새 Cart를 저장 (이후에는 이미 존재)
        } else {
            // 기존 Cart에 동일한 Product가 있는지 확인
            CartItem existingCartItem = cart.getCartItems().stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findFirst()
                    .orElse(null);

            if (existingCartItem != null) {
                // 동일 상품이 이미 있을 경우, 수량 업데이트
                int existingQuantity = existingCartItem.getQuantity();
                existingCartItem.setQuantity(existingQuantity + quantity);
                cart.addCartItem(existingCartItem);
//                cart.getCartItems().add(existingCartItem);
            } else {
                // 새 CartItem 생성 후 추가
                CartItem cartItem = CartItem.addCartItem(product, quantity);
                cart.addCartItem(cartItem);
//                cart.getCartItems().add(cartItem);
//                cart.addCart(member, cartItem);
            }

            return cartRepository.findCartByMember(memberId).getId();
        }
    }

    public Cart findCart(Long memberId) {
        return cartRepository.findCartByMember(memberId);
    }

    public void deleteCart(Long cartId) {
        Cart cart = cartRepository.findCartById(cartId);
        cartRepository.deleteCart(cart);
    }


}
