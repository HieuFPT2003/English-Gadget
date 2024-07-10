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
            justify-content: space-between;
            margin-bottom: 20px;
        }
        form input, form select {
            padding: 10px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 5px;
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
        .feedback-list {
            margin-top: 20px;
        }
        .user-review {
            display: flex;
            align-items: flex-start;
            margin-bottom: 20px;
        }
        .user-review img {
            border-radius: 50%;
            margin-right: 20px;
        }
        .review-content {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            flex: 1;
        }
        .review-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .review-header h3 {
            margin: 0;
        }
        .review-header small {
            color: #888;
        }
        .review-body {
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <header>
        <h1>User Feedback</h1>
    </header>
    <div class="container">
        <form action="usersearchfeedback" method="get">
            <input type="text" name="keyword" placeholder="Search by Name, Topic">
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
        <form action="LandingPage.jsp" method="get">
            <button type="submit">Home</button>
        </form>
        <div class="feedback-list">
            <c:forEach items="${feedback}" var="c">
                <div class="user-review">
                    <img src="images/default-avatar.png" alt="User Avatar" width="80" height="50">
                    <div class="review-content">
                        <div class="review-header">
                            <h3>User Name: ${c.username}</h3>
                            <small>Created at: ${c.createdAt}</small>
                        </div>
                        <div class="review-body">
                            <strong>${c.feedbackTopic}</strong>
                            <p>${c.feedbackText}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <jsp:include page="Footer.jsp" />
</body>
</html>
