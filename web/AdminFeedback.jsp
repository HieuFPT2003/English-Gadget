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
            form {
                display: flex;
                justify-content: flex-end;
                margin-bottom: 20px;
            }
            form button {
                padding: 10px;
                font-size: 1em;
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
            <script type="text/javascript">
                function doDelete(feedbackID) {
                    if (confirm("Are you sure you want to delete feedback with ID " + feedbackID + "?")) {
                        window.location = "deletefeedback?feedbackID=" + feedbackID;
                    }
                }
            </script>
<div class="container">
    <form action="searchfeedback" method="get">
        <input type="text" name="keyword" placeholder="Search by ID, Topic">
        <button type="submit">Search</button>
    </form>

    <form action="sortfeedback" method="get">
        <select name="sortBy">
            <option value="userID">UserID</option>
            <option value="feedbackTopic">Feedback Topic</option>
            <option value="created_at">Created Date</option>
        </select>
        <button type="submit">Sort</button>
    </form>

        </header>
        <div class="container">
            <form action="AdminLandingPage.jsp" method="get">
                <button type="submit">Home</button>
            </form>
            <table>
                <thead>
                    <tr>
                        <th>FeedBackID</th>
                        <th>UserID</th>
                        <th>FeedBackTopic</th>
                        <th>FeedBack</th>
                        <th>Create At</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${feedback}" var="c">
                        <tr>
                            <td>${c.feedbackID}</td>
                            <td>${c.userID}</td>
                            <td>${c.feedbackTopic}</td>
                            <td>${c.feedbackText}</td>
                            <td>${c.createdAt}</td>
                            <td class="center">
                                <a href="#" onclick="doDelete('${c.feedbackID}')">Delete</a><br/>
                                <a href="approvefeedback?feedbackID=${c.feedbackID}">Up Feedback</a>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <jsp:include page="Footer.jsp" />
    </body>
</html>
