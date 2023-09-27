package com.a02.sfams.config;

import com.a02.sfams.utils.JwtHelper;
import com.a02.sfams.utils.UserHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class RefreshTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取token
        String token = request.getHeader("token");
        //检查是否存在token
//        if(token==null)
//        {
//           return true;
//        }
        //保存到ThreadLocal中
        if(!StringUtils.isEmpty(token)){
            String userId = JwtHelper.getUserId(token);
            UserHolder.saveUser(userId);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}
