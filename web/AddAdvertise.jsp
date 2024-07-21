<%-- 
    Document   : AdminAdvertise
    Created on : Jul 7, 2024, 10:33:42 AM
    Author     : Admin
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>English Gadget</title>
        <jsp:include page="Header.jsp"></jsp:include>

            <style>
                /* Reset CSS */
                * {
                    margin: 0;
                    padding: 0;
                    box-sizing: border-box;
                }

                body {
                    font-family: Arial, sans-serif;
                    background-color: #f8f9fa;
                }

                header {
                    background-color: #DDD7CD;
                    color: black;
                    padding: 20px 0;
                    text-align: center;
                    margin-bottom: 2px;
                }

                header h1 {
                    font-family: 'Tilt Neon', sans-serif;
                    font-weight: bold;
                    margin: 0;
                    text-align: center;
                }

                .container {
                    max-width: 1200px;
                    margin: 0 auto;
                    padding: 20px;
                }

                .row {
                    display: flex;
                    justify-content: space-around;
                    margin-bottom: 20px;
                }

                .col {
                    flex: 1;
                    text-align: center;
                }

                input[type="text"],
                select,
                button,
                textarea {
                    font-family: 'Tilt Neon', sans-serif;
                    padding: 10px;
                    margin: 10px 0;
                    width: 100%;
                    border: 1px solid #ccc;
                    border-radius: 4px;
                    box-sizing: border-box;
                }

                button {
                    background-color: #588157;
                    color: white;
                    border: none;
                    cursor: pointer;
                    padding: 10px 20px;
                    border-radius: 5px;
                }

                button:hover {
                    background-color: #0056b3;
                }

                .table-container {
                    margin-top: 20px;
                    overflow-x: auto;
                }

                table {
                    width: 100%;
                    border-collapse: collapse;
                    background-color: white;
                }

                table th,
                table td {
                    border: 1px solid #dee2e6;
                    padding: 15px;
                    text-align: center;
                }

                table th {
                    background-color: #588157;
                    color: white;
                }

                table tbody tr:nth-child(odd) {
                    background-color: #f2f2f2;
                }

                .alert {
                    padding: 20px;
                    margin-top: 20px;
                    border-radius: 5px;
                    text-align: center;
                }

                .alert-success {
                    background-color: #d4edda;
                    color: #155724;
                }

                .alert-danger {
                    background-color: #f8d7da;
                    color: #721c24;
                }

                td a {
                    color: #007bff;
                    text-decoration: none;
                    font-weight: bold;
                    margin: 0 10px;
                }

                td a:hover {
                    text-decoration: underline;
                }

                td a.accept {
                    color: #28a745;
                }

                td a.decline {
                    color: #dc3545;
                }

                /* Form specific styles */
                .form-group {
                    margin-bottom: 20px;
                }

                .form-group label {
                    font-weight: bold;
                    display: block;
                }

                .form-group input[type="file"] {
                    width: auto;
                }

                .form-group textarea {
                    resize: vertical;
                }
                input[type="file"] {
                    font-family: 'Tilt Neon', sans-serif;
                    padding: 10px;
                    padding-right:665px;
                    margin: 10px 0;
                    width: 100%;
                    border: 1px solid #ccc;
                    border-radius: 4px;
                    box-sizing: border-box;
                    background-color: white;
                    cursor: pointer;
                }
            </style>
        </head>
        <body>
            <header>
                <h1>Add Advertisements</h1>
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <form action="AdminAdvertise">
                                <button type="submit">Advertisement list</button>
                            </form>
                        </div>
                        <div class="col">
                            <form action="AdminLandingPage.jsp" method="get">
                                <button type="submit">Home</button>
                            </form>
                        </div>
                    </div>
                </div>
            </header>
            <div class="container">
                <div class="col-lg-8 offset-lg-2">
                <c:if test="${not empty logsu}">
                    <div class="alert alert-success mt-3">${logsu}</div>
                </c:if>
                <c:if test="${not empty logfa}">
                    <div class="alert alert-danger mt-3">${logfa}</div>
                </c:if>
            </div>
            <div style ="padding-right: 100px; padding-left: 100px; padding-top: 2px">

                <form action="AddAdvertiseControl" method="post">
                    <div class="form-group">
                        <label for="title">Title:</label>
                        <input type="text" id="title" name="title" placeholder="Enter Title" required>
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea id="description" name="description" rows="4" placeholder="Enter Description" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="imageUrl">Image URL:</label>
                        <input type="text" id="imageUrl" name="imageUrl" placeholder="Enter Image URL" required>
                    </div>
                    <div class="form-group">
                        <label for="isActive">Active:</label>
                        <select id="isActive" name="isActive" required>
                            <option value="" disabled selected hidden>Please select</option>
                            <option value="1">Active</option>
                            <option value="0">Not Active</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="adminName">Admin Name:</label>
                        <input type="text" id="adminName" name="adminName" value="${sessionScope.username}" readonly>
                    </div>
                    <div class="form-group">
                        <input type="text" id="adminID" name="adminID" value="${sessionScope.userID}" hidden readonly>
                    </div>
                    <button type="submit">Add Advertisement</button>
                </form>
            </div>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>

