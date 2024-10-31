package com.mookShopping.mook.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    private LocalDateTime saveCartTime;

    private int quantity;

    public static Cart addCart(Member member,CartItem... cartItems) {
        Cart cart = new Cart();
        cart.setMember(member);
        for (CartItem cartItem : cartItems) {
            cart.addCartItem(cartItem);
        }
        cart.setSaveCartTime(LocalDateTime.now());
        return cart;
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }

    public void setMember(Member member) {
        this.member = member;
        member.setCart(this);
    }
}
