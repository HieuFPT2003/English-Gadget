<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     <link rel="icon" href="images/logoTab-01.png" type="images/x-icon">
    <title>English Gadget</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: white;
            padding: 33px;
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
    <a style="float: left;text-decoration: none" href="LandingPage.jsp" >
        <img style="height: 33px;margin-top: -14px"src="images/logofixfinal.png" alt=""/>
    </a> 

   
</header>
<div class="container">
    <h1 style="text-align:center">User Profile</h1>
    
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
    <a href="userupdate?userID=${user.userID}" class="btn-home">Update</a>
    <a href="changepass.jsp" class="btn-home">Change Password</a>
</div>
<jsp:include page="Footer.jsp" />
</body>
</html>
