//package com.neu.interceptors;
//
//
//import com.neu.service.TokenService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//
///**
// * @author: treblez
// * @className: TokenInterceptor
// * @description: 拦截器校验token
// * @data: 2020-04-07
// **/
//@Component
//public class TokenInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private TokenService tokenService;
//
//
//    private ArrayList<String> unCheckUrlNormal = new ArrayList<String>() {{
//
//    }};
//
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//
//        String token = httpServletRequest.getHeader("token");
//        String url = httpServletRequest.getRequestURI();
//        String username = httpServletRequest.getParameter("username");
//
////      使用正则表达式匹配url
//
//        boolean tokenValidity = tokenService.checkToken(Long.parseLong(username),token);
////      没有通过，返回错误，最后解除注释
////        if(!tokenValidity){
////            throw new TokenInvalidException();
////        }
//
//
//        return true;
//    }
//
//
//}
