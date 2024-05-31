<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>List of Users</title>
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
                display: flex;
                justify-content: space-between;
                width: 80%;
                margin: 20px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .form-container {
                flex: 1;
                margin-right: 20px;
            }
            .table-container {
                flex: 2;
            }
            form {
                display: flex;
                flex-direction: column;
                margin-bottom: 20px;
            }
            form input, form select, form button {
                padding: 10px;
                font-size: 1em;
                margin-bottom: 10px;
            }
            form button {
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            form button:hover {
                background-color: #45a049;
            }
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                padding: 12px;
                border: 1px solid #ddd;
                text-align: center;
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
            td a {
                text-decoration: none;
                color: #4CAF50;
            }
            td a:hover {
                color: #45a049;
            }
        </style>
    </head>
    <body>
        <header>
            <h1>List of Users</h1>
        </header>   
        <div class="container">
            <div class="form-container">
                <form action="profile" method="get">
                </form>
            </div>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Password</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Age</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${users}" var="c">
                            <tr>
                                <td>${c.username}</td>
                                <td>${c.email}</td>
                                <td>${c.password}</td>
                                <td>${c.phone}</td>
                                <td>${c.address}</td>
                                <td>${c.age}</td>
                                <td class="center">
                                    <a href="update?userID=${c.userID}">Update</a>&nbsp;
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>
