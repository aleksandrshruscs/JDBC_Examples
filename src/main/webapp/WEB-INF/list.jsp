<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 14.04.2020
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/list" method="get">
    <p>
        <label for="search">Search in DB</label>
        <input id="search" type="text" name="search" required>
        <button type="submit">SEARCH</button>
    </p>
</form>
<p></p>
<c:if test="${not empty requestScope.searchList}">
    Search results:
<table>
    <tr>
        <th>id</th>
        <th>last name</th>
        <th>first name</th>
        <th>phone nr</th>
        <th>email</th>
        <th>salary</th>
    </tr>
    <c:forEach items="${requestScope.searchList}" var="emp">
        <tr>
            <td>${emp.id}</td>
            <td>${emp.lastName}</td>
            <td>${emp.firstName}</td>
            <td>${emp.phoneNr}</td>
            <td>${emp.email}</td>
            <td>${emp.salary}</td>
        </tr>
    </c:forEach>
</table>
</c:if>
<c:if test="${not empty requestScope.notFound}">
    ${requestScope.notFound}
</c:if>
<p></p>
<p></p>
All positions in DB:
<table>
    <tr>
        <th>id</th>
        <th>last name</th>
        <th>first name</th>
        <th>phone nr</th>
        <th>email</th>
        <th>salary</th>
    </tr>
    <c:forEach items="${requestScope.list}" var="emp">
        <tr>
            <td>${emp.id}</td>
            <td>${emp.lastName}</td>
            <td>${emp.firstName}</td>
            <td>${emp.phoneNr}</td>
            <td>${emp.email}</td>
            <td>${emp.salary}</td>
        </tr>
    </c:forEach>
</table>
<a href="/create.jsp">Add new employee</a>
</body>
</html>
