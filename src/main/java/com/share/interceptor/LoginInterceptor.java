//package com.share.interceptor;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
////@Component
//public class LoginInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        try {
//            System.out.println("拦截器生效了！");
//            List<String> asList = Arrays.asList("/user/loginusername", "/user/logintel", "/user/registerUsername" ,"/user/registertel","/user/verifyCode","/rent/**");
//            String uri = request.getRequestURI();
//            //1.设置放行路径
//            if(asList.contains(uri)){
//                return true;
//            }
//
//            HttpSession session = request.getSession(true);
//
//            if(session.getAttribute("userInfo")  == null){
//                response.setContentType("application/json; charset=utf-8");
//                response.getWriter().print("{\"code\":401\",data\":\"登录信息失效\",\"msg\":\"fail\"}");
//                return false;
//            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//
//
//    }
//}
