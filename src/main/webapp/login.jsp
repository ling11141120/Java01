<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ling1
  Date: 2025/6/4
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false"  language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="loginServlet" method="post">
    用户名 ：<input type="text" name="uname">
    密码 ：<input type="password" name="pwd">
          <input type="submit" value="登录"><br>
           ${requestScope.msg}
</form>

</body>
</html>
