<%-- 
    Document   : Test
    Created on : May 23, 2024, 9:52:12 PM
    Author     : Q.Hieu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach items="${listPost}" var="p">
            <p>${p}</p>
        </c:forEach>
    </body>
</html>
