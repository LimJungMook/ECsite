package com.mookShopping.mook.service;

import com.mookShopping.mook.domain.Member;
import com.mookShopping.mook.domain.Order;
import com.mookShopping.mook.domain.OrderProduct;
import com.mookShopping.mook.domain.Product;
import com.mookShopping.mook.repository.MemberRepository;
import com.mookShopping.mook.repository.OrderRepository;
import com.mookShopping.mook.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long saveOrder(Long memberId, Long productId, int count) {
        Member member = memberRepository.findOne(memberId);
        Product product = productRepository.findOneProduct(productId);

        OrderProduct orderProduct = OrderProduct.createProduct(product, product.getPrice(), count);

        Order order = Order.createOrder(member, orderProduct);
        return orderRepository.saveOrder(order);
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancelOrder();
    }

}
