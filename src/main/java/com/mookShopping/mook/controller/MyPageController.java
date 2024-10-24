package com.mookShopping.mook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {
    @GetMapping("/myPage")
    public String myPageForm(Model model) {
        // insert code
        // 세션에서 받아서 멤버 가져오는 것
        return "myPage";
    }
}
