package com.msb;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * &#064;Author:  LingWeiBo
 * &#064;Date:  2025/6/5 16:11
 */
@WebFilter("/*")
public class Filter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        javax.servlet.Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        javax.servlet.Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        if (uri.contains("login.jsp")||uri.contains("/images")||uri.contains("loginServlet")){
            filterChain.doFilter(request,  response);
            return;
        }
        Object user = request.getSession().getAttribute("user");
        if (user!=null){
            filterChain.doFilter(request, response);
            return;
        }

        response.sendRedirect("/login.jsp");
    }
}
