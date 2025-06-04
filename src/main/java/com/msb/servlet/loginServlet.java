package com.msb.servlet;

import com.msb.dao.UserDao;
import com.msb.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * &#064;Author:  LingWeiBo
 * &#064;Date:  2025/6/4 15:34
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受页面传输过来的数据
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        System.out.println(uname);
        System.out.println(pwd);
        //链接数据库
        UserDao  userDao=new UserDao();
        User user;
        try {
            user = userDao.selectOne(uname, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(user);
        //根据数据处理的结果响应数据库
        if (user!=null){
            //跳转到项目首页重定向
            req.getSession().setAttribute("user",user);//存储对象
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }else{
           req.setAttribute("msg","用户名或密码错误");
           req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }
}
