package com.mookShopping.mook.controller;

import com.mookShopping.mook.domain.Member;
import com.mookShopping.mook.web.MemberForm;
import com.mookShopping.mook.web.MyPageForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {
    @GetMapping("/myPage")
    public String myPageForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member loginMember = (Member)session.getAttribute("loginMember");
        if (null == loginMember) {
            return "main";
        }
        MyPageForm myPageForm = new MyPageForm();
        myPageForm.setName(loginMember.getName());
        myPageForm.setMail(loginMember.getMail());
        myPageForm.setPassword(loginMember.getPassword());
        myPageForm.setAddress(loginMember.getAddress());
        model.addAttribute("memberForm", myPageForm);
        return "myPage";
    }
}
