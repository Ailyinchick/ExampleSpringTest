<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  Planet: vladimirlozickiy
  Date: 2019-08-09
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1 align="center">Welcome to home page</h1>

<form action="/findById" method="post">
    <input type="text" name="id" width="50"/>
    <input type="submit" value="Find by ID">
    <h1> ${userById} </h1>
</form>

<form action="/findRichest">
    <input type="submit" value="Show Richest">
    <h1> ${richestUser} </h1>
</form>

<form action="/findAll">
    <input type="submit" value="Display all">
    <div> ${allUsers} </div>

</form>

<form action="/bankSumm">
    <input type="submit" value="Bank summ">
    <h1> ${bankSumm} </h1>
</form>

</body>
</html>
