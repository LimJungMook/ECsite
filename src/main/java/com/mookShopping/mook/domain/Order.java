package com.mookShopping.mook.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProductList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public static Order createOrder(Member member, OrderProduct... orderProducts) {
        Order order = new Order();
        order.setMember(member);
        for (OrderProduct orderProduct : orderProducts) {
            order.addOrderProduct(orderProduct);
        }
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.ORDER);
        return order;
    }

    private void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    private void addOrderProduct(OrderProduct orderProduct) {
        orderProductList.add(orderProduct);
        orderProduct.setOrder(this);
    }

    public void cancelOrder() {
        this.orderStatus = OrderStatus.CANCEL;
        for(OrderProduct orderProduct : orderProductList){
            orderProduct.cancel();
        }
    }
//    CREATE TABLE orders(
//            order_ID INT PRIMARY KEY,
//            memberId int,
//            orderStatus int,
//            orderDate date
//    );
}
