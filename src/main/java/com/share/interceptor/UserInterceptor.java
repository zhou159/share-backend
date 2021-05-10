package com.share.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.share.annotation.PassToken;
import com.share.annotation.UserLoginInfo;
import com.share.annotation.UserLoginToken;
import com.share.entity.User;
import com.share.exceptions.ShareException;
import com.share.exceptions.UserNotLoginException;
import com.share.ro.userRo.UserRo;
import com.share.service.UserService;
import com.share.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;


@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        //从请求头中拿取token
        String token_UUID = request.getHeader("token");

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //检查有没有需要用户登录注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            System.out.println("进入了用户验证");

            //从redis中拿取token真实值
            if (token_UUID==null){
                throw new UserNotLoginException("用户信息已过期！请重新登录");
            }
            String token = String.valueOf(redisUtil.get(token_UUID));

            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new UserNotLoginException("请登录!");
                }
                // 获取 token 中的 user id
                int userId_token;
                try {
                    userId_token = JWT.decode(token).getClaim("id").asInt();
                } catch (JWTDecodeException j) {
                    throw new UserNotLoginException("请登录!");
                }

                UserRo user = userService.queryUserById(userId_token);
                if (user == null) {
                    throw new UserNotLoginException("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(String.valueOf(user.getId()))).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new UserNotLoginException("请登录!");
                }
                return true;
            }

        }

        //检查有没有需要用户信息的注解
        if (method.isAnnotationPresent(UserLoginInfo.class)){
            System.out.println("进入了用户身份验证");
            Map attribute =(Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            int userId = Integer.valueOf((String) attribute.get("userId"));
            System.out.println(userId);

            //从redis中拿取token真实值
            if (token_UUID==null){
                throw new UserNotLoginException("用户信息已过期！请重新登录");
            }
            String token2 = String.valueOf(redisUtil.get(token_UUID));

            UserLoginInfo userLoginToken2 = method.getAnnotation(UserLoginInfo.class);
            if (userLoginToken2.required()) {
                // 执行认证
                if (token2 == null) {
                    throw new UserNotLoginException("请登录!");
                }
                // 获取 token 中的 user id
                int userId_token;
                try {
                    userId_token = JWT.decode(token2).getClaim("id").asInt();
                } catch (JWTDecodeException j) {
                    throw new UserNotLoginException("请登录!");
                }

                UserRo user = userService.queryUserById(userId_token);
                if (user == null) {
                    throw new UserNotLoginException("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(String.valueOf(user.getId()))).build();
                try {
                    jwtVerifier.verify(token2);
                } catch (JWTVerificationException e) {
                    throw new UserNotLoginException("请登录!");
                }

                if (userId!=userId_token){
                    throw new UserNotLoginException("请登录正确账户！");
                }
                return true;
            }
        }



        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
    //    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
////        try {
////            System.out.println("拦截器生效了！");
////            List<String> asList = Arrays.asList("/user/loginusername", "/user/logintel", "/user/registerUsername" ,"/user/registertel","/user/verifyCode","/rent/**");
////            String uri = request.getRequestURI();
////            //1.设置放行路径
////            if(asList.contains(uri)){
////                return true;
////            }
////
////            HttpSession session = request.getSession(true);
////
////            if(session.getAttribute("userInfo")  == null){
////                response.setContentType("application/json; charset=utf-8");
////                response.getWriter().print("{\"code\":401\",data\":\"登录信息失效\",\"msg\":\"fail\"}");
////                return false;
////            }
////
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
//        return false;
//
//
//
//
//    }
}
