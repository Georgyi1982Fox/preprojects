<%--
  Created by IntelliJ IDEA.
  User: georg
  Date: 23.03.2020
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>User Home Page</title>
</head>


<body>

<center>
</center>

<div align="center">

<form action="/login" method="POST">

    <table border="1" cellpadding="5">

        <h2>Authorization Page</h2>
        <p><b>Login:</b><br>
            <input type="text" name ="login" size="30"></p>
        <p><b>Password:</b><br>
            <input type="password" name ="password" size="30"></p>
        <p><input type="submit" value="ОK"
                  onclick="window.location='admin.jsp';" />


         </table>

       </form>

</div>

</body>

</html>

