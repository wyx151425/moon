package com.rumofuture.moon.util.interceptor;

import com.rumofuture.moon.domain.User;
import com.rumofuture.moon.util.constant.KeyConstant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MoonMVCInterceptor implements HandlerInterceptor {

    private static final String[] URLS = {"/mvc/login", "/mvc/signUp"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;

        String servletPath = request.getServletPath();
        if (servletPath.contains("/mvc")) {
            for (String url : URLS) {
                if (servletPath.contains(url)) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                User currentUser = (User) request.getSession().getAttribute(KeyConstant.CURRENT_USER);
                if (null == currentUser) {
                    response.sendRedirect(request.getContextPath() + URLS[0]);
                } else {
                    flag = true;
                }
            }
        } else {
            flag = true;
        }

        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
