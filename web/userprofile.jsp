<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #4CAF50;
            padding: 20px;
            color: white;
            text-align: center;
        }
        h1 {
            margin: 0;
            font-size: 2em;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .error {
            color: red;
            font-weight: bold;
        }
        .btn-home {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 10px;
        }
        .btn-home:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<header>
    <h1>User Profile</h1>
</header>
<div class="container">
    <c:if test="${not empty user}">
        <table>
            <tr>
                <th>Username</th>
                <td>${user.username}</td>
            </tr>
            <tr>
                <th>Email</th>
                <td>${user.email}</td>
            </tr>
            <tr>
                <th>Phone</th>
                <td>${user.phone}</td>
            </tr>
            <tr>
                <th>Address</th>
                <td>${user.address}</td>
            </tr>
            <tr>
                <th>Age</th>
                <td>${user.age}</td>
            </tr>
            <tr>
                <th>Created At</th>
                <td>${user.createdAt}</td>
            </tr>
            <tr>
                <th>Premium</th>
                <td>${user.premiumID}</td>
            </tr>
        </table>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <p class="error">${errorMessage}</p>
    </c:if>
    <a href="LandingPage.jsp" class="btn-home">Home</a>
    <a href="userupdate?userID=${user.userID}" class="btn-home">Update</a>
</div>
<jsp:include page="Footer.jsp" />
</body>
</html>
