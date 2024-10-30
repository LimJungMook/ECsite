package com.mookShopping.mook.repository;

import com.mookShopping.mook.domain.Order;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    @Autowired
    EntityManager em;

    public Long saveOrder(Order order) {
        em.persist(order);
        return order.getId();
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findByMemberId(Long id) {
        return em.createQuery("select o from Order o where o.member.id =: memberId")
                .setParameter("memberId", id)
                .getResultList();
    }
}
