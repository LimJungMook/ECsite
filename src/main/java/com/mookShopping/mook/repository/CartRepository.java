package com.mookShopping.mook.repository;

import com.mookShopping.mook.domain.Cart;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepository {

    @Autowired
    private EntityManager em;

    public Long saveCart(Cart cart) {
        em.persist(cart);
        return cart.getId();
    }

    public Cart findCartById(Long cartId) {
        return em.find(Cart.class, cartId);
    }

    public List<Cart> findCartByMember(Long memberId) {
        return em.createQuery("select c from Cart c where memberId := memberId", Cart.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public void deleteCart(Cart cart) {
        em.remove(cart);
    }
}
