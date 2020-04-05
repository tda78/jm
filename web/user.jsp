<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 04.04.2020
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User information</title>
</head>
<body>
<H2>User information</H2>

  <h2>ID: ${user.id}</h2>
  <h2>name: ${user.name}</h2>
  <h2>password: ${user.password}</h2>
  <h2>role: ${user.role}</h2>
  <form action="/login" method="get">
    <input type="submit" value="back">
  </form>
</body>
</html>
