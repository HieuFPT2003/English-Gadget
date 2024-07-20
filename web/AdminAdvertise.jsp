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
            <script type="text/javascript">
                function doDelete(id) {
                    if (confirm("Are you sure you want to delete advertise with ID=" + id + "?")) {
                        window.location = "DeleteAdvertise?adID=" + id;
                    }
                }
            </script>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f8f9fa;
                    margin: 0;
                    padding: 0;
                }

                header {
                    background-color: #DDD7CD;
                    color: white;
                    padding: 20px 0;
                    text-align: center;
                }

                header h1 {
                    font-family: 'Tilt Neon', sans-serif;
                    color: black;
                    margin: 0;
                    font-weight: bold;
                }

                .container {
                    margin-top: 20px;
                    padding: 20px;
                    max-width: 1200px;
                    margin-left: auto;
                    margin-right: auto;
                }

                .row {
                    display: flex;
                    justify-content: space-around;
                    margin-bottom: 20px;
                }

                .row .col {
                    flex: 1;
                    text-align: center;
                }

                input[type="text"], select, button {
                    font-family: 'Tilt Neon', sans-serif;
                    padding: 10px;
                    margin: 10px 0;
                    width: 80%;
                }

                button {
                    font-family: 'Tilt Neon', sans-serif;
                    background-color: #588157;
                    border: none;
                    color: white;
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
                    border-collapse: collapse;
                    margin: 0 auto;
                    width: 100%;
                    max-width: 100%;
                    background-color: white;
                    table-layout: auto;
                }

                table th, table td {
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
                    margin: 0 10px;
                    color: #007bff;
                    text-decoration: none;
                    font-weight: bold;
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
                .action-buttons {
                    display: flex;
                    justify-content: center;
                    align-items: center;
                }

                .action-buttons a {
                    margin: 0 5px;
                    padding: 5px 10px;
                    border: 1px solid #ccc;
                    border-radius: 4px;
                    text-decoration: none;
                    color: #007bff;
                    font-weight: bold;
                    cursor: pointer;
                }

                .action-buttons a:hover {
                    background-color: #f0f0f0;
                }
            </style>
        </head>
        <body>
            <header>

                <h1>List of Advertisements</h1>
                <div class="container text-center">
                    <div class="row">
                        <div class="col">
                            <form action="SearchAdvertise" method="post">
                                <input type="text" name="title" placeholder="Search title">
                                <button type="submit">Search</button>
                            </form>
                        </div>
                        <div class="col">
                            <form action="AdminAdvertise" method="post">
                                <select name="filter">
                                    <option value="" disabled selected hidden>Select a type</option>
                                    <option value="1">Active</option>
                                    <option value="0">Not Active</option>
                                    
                                </select>
                                <button type="submit">Filter status</button>
                            </form>
                        </div>
                        <div class="col">
                            <form action="AddAdvertise.jsp" method="">
                                <button type="submit">Add Advertisement</button>
                            </form>
                            <form action="DeleteAllAdvertise" method="post">
                                <button type="submit">Delete All Advertise</button>
                            </form>
                        </div>
                        <div class="col">
                            <form action="AdminAdvertise">
                                <button type="submit">Advertise list</button>
                            </form>
                            <form action="AdminLandingPage.jsp" method="get">
                                <button type="submit">Home</button>
                            </form>
                        </div>
                    </div>
                </div>
            </header>
            <div class="col-lg-8 offset-lg-2">
            <c:if test="${not empty logsu}">
                <div class="alert alert-success mt-3" style="text-align: center">${logsu}</div>
            </c:if>
            <c:if test="${not empty logfa}">
                <div class="alert alert-danger mt-3"  style="text-align: center">${logfa}</div>
            </c:if>
        </div>
        <div class="container">

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Image</th>
                        <th>Status</th>
                        <th>Admin</th>
                        <th>Created Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${list}" var="c">
                        <tr>
                            <td>${c.adID}</td>   
                            <td>${c.title}</td>
                            <td>${c.description}</td>
                            <td><img src="${c.imageAd}" style="width:300px;height:250px"></td>
                            <td>
                                <c:choose>
                                    <c:when test="${c.isActive}">
                                        Active
                                    </c:when>
                                    <c:otherwise>
                                        Not Active
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${c.userName}</td>
                            <td>${c.created_at}</td>
                            <td class="action-buttons">
                                <a href="#" onclick="doDelete('${c.adID}')">Delete</a>
                                <a href="UpdateAdvertise?adID=${c.adID}" role="button">Update</a>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
        <jsp:include page="Footer.jsp"></jsp:include>

    </body>
</html>

