package com.msb.filter;

import com.msb.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class loginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化逻辑
        System.out.println("loginFilter 初始化完成");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
         //基于HTTP的请求对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //设置放行资源
        //通过地址栏请求的地址判断是否携带
        String uri = request.getRequestURI();
        if (uri.contains("login.jsp") || uri.contains("/loginServlet") || uri.contains("register.jsp") || uri.contains("registerServlet")) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
       User user = (User)request.getSession().getAttribute("user");
        if (user != null) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //非法访问重定向到 login.jsp
        response.sendRedirect("/login.jsp");
    }

    @Override
    public void destroy() {
        System.out.println("loginFilter 被销毁");
    }
}


