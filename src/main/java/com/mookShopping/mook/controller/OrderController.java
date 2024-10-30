package com.mookShopping.mook.controller;

import com.mookShopping.mook.domain.Member;
import com.mookShopping.mook.domain.Order;
import com.mookShopping.mook.service.OrderService;
import com.mookShopping.mook.web.OrderForm;
import com.mookShopping.mook.web.ProductForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/new")
    public String orderForm(Model model, @Valid ProductForm productForm) {
        OrderForm orderForm = new OrderForm();
        orderForm.setProduct(productForm);
        model.addAttribute("orderForm", orderForm);
        return "order";
    }

    @PostMapping("/order/order")
    public String order(OrderForm orderForm, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member loginMember = (Member)session.getAttribute("loginMember");
        Long savedOrder = orderService.saveOrder(loginMember.getId(), orderForm.getProduct().getId(), orderForm.getProduct().getQuantity());
        log.info("savedOrder= {}", savedOrder);

        return "redirect:/";
    }

    @GetMapping("/order/history")
    public String orderHistoryForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member loginMember = (Member)session.getAttribute("loginMember");

        List<Order> historyList = orderService.findOrderByMemberId(loginMember.getId());
        model.addAttribute("order", historyList);
        return "record";
    }

    @GetMapping("order/cancel/{id}")
    public String cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return "redirect:/order/history";
    }
}
