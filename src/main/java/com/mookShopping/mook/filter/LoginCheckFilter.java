package com.mookShopping.mook.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whiteList = {"/", "/member/login","/logout","/product/orderProductDetail/*"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        try {
            log.info("인증필터 시작: {}", requestURI);
            if (isLoginCheck(requestURI)) {
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute("loginMember") == null) {
                    log.info("미인증 사용자 요청 {} ", requestURI);
                    httpResponse.sendRedirect("/member/login?redirectURL=" + requestURI);
                    return;
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            throw e;
        }finally {
            log.info("인증 체크 필터 완료");

        }
    }

    private boolean isLoginCheck(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}
