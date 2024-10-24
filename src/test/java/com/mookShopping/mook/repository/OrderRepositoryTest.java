package com.mookShopping.mook.repository;

import com.mookShopping.mook.domain.*;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;


class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void saveOrder() {
        //given
        Member member = new Member();
        member.setName("name");
        member.setPassword("aaa");
        Product product = new Product();
        product.setName("tea");
        product.setPrice(2300L);
        product.setQuantity(34L);
        OrderProduct orderProduct = OrderProduct.createProduct(product, product.getPrice(), 2);
        Order order = Order.createOrder(member, orderProduct);

        //when
        Long savedOrder = orderRepository.saveOrder(order);

        //then
        Assertions.assertThat(savedOrder).isNotNull();
        Assertions.assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.ORDER);
    }

    @Test
    void findOne() {
    }
}