<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
<!-- Basic -->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="icon" href="images/logoTab-01.png" type="images/x-icon">
        <title>English Gadget</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
        <link href="css/font-awesome.min.css" rel="stylesheet" />
        <link href="css/style.css" rel="stylesheet" />
        <link href="css/responsive.css" rel="stylesheet" />
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
                <img style="height: 33px;margin-top: -14px" src="images/logofixfinal.png" alt=""/>
                <a href="PaymentServlet" style="float: right;color: black">Show Order</a>
            </a> 
        </header>
        <div class="container">
            <h1 style="text-align:center">Payment Pending</h1>

            <c:if test="${not empty users}">
                <table>
                    <tr>
                        <th>ID</th>
                        <td>${users.userID}</td>
                    </tr>
                    <tr>
                        <th>Username</th>
                        <td>${users.username}</td>
                    </tr>
                    <tr>
                        <th>Premium</th>
                        <td>
                            <c:choose>
                                <c:when test="${users.premiumID}">
                                    <span style="color:green" class="status-active">Approved</span>
                                </c:when>
                                <c:otherwise>
                                    <span style="color:red" class="status-pending">Pending</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <th>Random Code</th>
                        <td>${randomCode}</td>
                    </tr>

                </table>
            </c:if>


            <c:if test="${not empty errorMessage}">
                <p class="error">${errorMessage}</p>
            </c:if>   
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>

        <!-- jQuery -->
        <script src="js/jquery-3.4.1.min.js"></script>
        <!-- popper js -->
        <script src="js/popper.min.js"></script>
        <!-- bootstrap js -->
        <script src="js/bootstrap.js"></script>
        <!-- custom js -->
        <script src="js/custom.js"></script>
    </body>
</html>
