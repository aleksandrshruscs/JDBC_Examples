<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 14.04.2020
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/create" method="post">
    <p>
        <label for="id">ID</label>
        <input id="id" type="number" name="id" required>
    </p>
    <p>
        <label for="firstName">First Name</label>
        <input id="firstName" type="text" name="firstName" required>
    </p>
    <p>
        <label for="lastName">Last Name</label>
        <input id="lastName" type="text" name="lastName" required>
    </p>
    <p>
        <label for="phoneNr">Phone Number</label>
        <input id="phoneNr" type="text" name="phoneNr">
    </p>
    <p>
        <label for="email">E-Mail</label>
        <input id="email" type="email" name="email">
    </p>
    <p>
        <label for="salary">Salary</label>
        <input id="salary" type="number" name="salary" required>
    </p>
    <button type="submit">Add</button>
</form>
</body>
</html>
