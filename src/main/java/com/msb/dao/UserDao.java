package com.msb.dao;

import com.msb.pojo.User;

import java.sql.*;

import static java.lang.Class.forName;

/**
 * &#064;Author:  LingWeiBo
 * &#064;Date:  2025/6/4 15:37
 */
public class UserDao {
    //查询学生信息
    public User selectOne(String uname, String pwd) throws ClassNotFoundException, SQLException {
        User user=null;

        //加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取链接
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/chaxun?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true","root","258069");
        //创建会话
        PreparedStatement ps = conn.prepareStatement("select * from t_user where uname=? and pwd=?");
        ps.setString(1,uname);
        ps.setString(2,pwd);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            String username = rs.getString("uname");
            int uid = rs.getInt("uid");
            String password = rs.getString("pwd");
            user = new User(uid,username,password);
        }
        rs.close();
        ps.close();
        conn.close();
        System.out.println("查询成功");
        return user;
    }
}
