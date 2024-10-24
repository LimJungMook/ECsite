package com.mookShopping.mook.controller;

import com.mookShopping.mook.domain.Member;
import com.mookShopping.mook.service.MemberService;
import com.mookShopping.mook.web.MemberForm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/member/new")
    public String newMemberForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "register";
    }

    @PostMapping("/member/new")
    public String saveMember(@Valid MemberForm memberForm, BindingResult result) {

        if (result.hasErrors()) {
            return "register";
        }
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setPassword(memberForm.getPassword());
        member.setMail(memberForm.getMail());
        Long savedMemberId = memberService.saveMember(member);
        if (savedMemberId.equals(null)) {

        }
        return "redirect:/myLogin";
    }

    public String loginMember(@Valid MemberForm memberForm, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "myLogin";
        }

        Member member = new Member();
        member.setName(memberForm.getName());
        member.setPassword(memberForm.getPassword());
        Member loginMember = memberService.login(member);

        if (loginMember == null) {
            bindingResult.reject("id,password를 확인해주세요");
        }

        HttpSession session = request.getSession();
        session.setAttribute("", loginMember);

        return "main";
    }

    @PostMapping("/logout")
    public String logoutMember(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/main";
    }

//    private void expireCookie(HttpServletResponse response,String memberId) {
//        Cookie cookie = new Cookie(memberId, null);
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//    }
}
