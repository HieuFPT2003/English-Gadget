<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Boolean role = (Boolean) session.getAttribute("role");
    if (role == null || !role) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="Header.jsp" />
    </head>
    <script>
        var userID = <%= session.getAttribute("userID") != null ? "\"" + session.getAttribute("userID") + "\"" : "null" %>;
        var premium = <%= session.getAttribute("premium") != null ? "\"" + session.getAttribute("premium") + "\"" : "null" %>;
        var role = <%= session.getAttribute("role") != null ? "\"" + session.getAttribute("role") + "\"" : "null" %>;
        var name = <%= session.getAttribute("usernamegoogle") != null ? "\"" + session.getAttribute("usernamegoogle") + "\"" : "null" %>;

        console.log("User ID: " + userID);
        console.log("Premium: " + premium);
        console.log("Role: " + role);
        console.log("Name: " + name);
    </script>
<head>
    <meta charset="UTF-8">
    <title>Update User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
        }
        label, input, select {
            display: block;
            width: 100%;
            margin-bottom: 10px;
        }
        label {
            font-weight: bold;
        }
        input[type="text"], input[type="email"], input[type="tel"], input[type="number"], select {
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[readonly] {
            background-color: #f0f0f0; /* Gray background for read-only */
            pointer-events: none; /* Disable editing */
        }
        input[type="submit"] {
            background-color: #5cb85c;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>Update User</h1>
    <form action="update" method="post">
        <input type="hidden" name="userID" value="${user.userID}" />
        
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${user.username}" readonly />

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${user.email}" readonly />

        <label for="password">Password:</label>
        <input type="text" id="password" name="password" value="${user.password}" readonly />

        <label for="phone">Phone:</label>
        <input type="tel" id="phone" name="phone" value="${user.phone}" readonly />

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${user.address}" readonly />

        <label for="age">Age:</label>
        <input type="number" id="age" name="age" value="${user.age}" readonly />

        <label for="premiumID">Premium ID:</label>
        <select id="premiumID" name="premiumID" >
            <option value="1" ${user.premiumID ? 'selected' : ''}>Yes</option>
            <option value="0" ${!user.premiumID ? 'selected' : ''}>No</option>
        </select>

        <input type="submit" value="Update User" />
    </form>
    <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
