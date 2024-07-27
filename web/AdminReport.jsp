<%-- 
    Document   : AdminReport
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
                function doApprove(id) {
                    if (confirm("Are you sure you want to approve this request ?")) {
                        window.location = "delPostReported?pid=" + id;
                    }
                }
                function doReject(id) {
                    if (confirm("Are you sure you want to reject this request ?"+id)) {
                        window.location = "delreport?postID=" + id;
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

            </style>
        </head>
        <body>
            <header>
                <h1>List of Reports</h1>
                <div class="container text-center">
                    <div class="row">
                        <div class="col">
                            <form action="searchReport" method="post">
                                <input type="text" name="message" placeholder="Search message">
                                <button type="submit">Search</button>
                            </form>
                        </div>
                        <div class="col">
                            <form action="searchReport" method="get">
                                <select name="filter">
                                    <option value="" disabled selected hidden>Select a type</option>
                                    <option value="Spam">Spam</option>
                                    <option value="Inappropriate">Inappropriate Content</option>
                                    <option value="False Information">False Information</option>
                                    <option value="Harassment">Harassment</option>
                                    <option value="Privacy Violation">Privacy Violation</option>
                                    <option value="Copyright Violation">Copyright Violation</option>
                                    <option value="Hate Speech">Hate Speech</option>
                                    <option value="Community Guidelines Violation">Community Guidelines Violation</option>
                                    <option value="Financial Scam">Financial Scam</option>
                                    <option value="Insulting Communist Party">Insulting the Communist Party of Vietnam</option>
                                    <option value="Other">Other</option>
                                </select>
                                <button type="submit">Report Type</button>
                            </form>
                        </div>
                        <div class="col">
                            <form action="SortReport" method="post">
                                <select name="sort">
                                    <option value="" disabled selected>Select</option>
                                    <option value="Asc">Asc</option>
                                    <option value="Desc">Decs</option>
                                </select>
                                <button type="submit">Sort Create_At</button>
                            </form>
                        </div>
                        <div class="col">
                            <form action="ReportPostServlet">
                                <button type="submit">Report list</button>
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


                        <th>User Report</th>
                        <th>User</th>
                        <th>Post Content</th>
                        <th>Report Type</th>
                        <th>Message</th>
                        <th>Create At</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${listReport}" var="c">
                        <tr>
                            <td>${c.userReportName}</td>   
                            <td>${c.username}</td>
                            <td>${c.postText}</td>
                            <td>${c.reportType}</td>
                            <td>${c.message}</td>
                            <td>${c.create_at}</td>
                            <td class="center">

                                <a class = "btn btn-primary" href ="#" onclick = "doApprove('${c.postID}')" >
                                    Approve

                                </a>                        
                                   
                                <a class="btn btn-danger" href ="#" onclick = "doReject('${c.postID}')">
                                    Reject
                                </a>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
        <jsp:include page="Footer.jsp"></jsp:include>

    </body>
</html>

