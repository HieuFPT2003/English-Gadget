<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="Header.jsp" />
    <meta charset="UTF-8">
    <title>User Feedback</title>
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
        .search-form, .sort-form {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }
        .search-form input, .sort-form select {
            padding: 10px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .search-form button, .sort-form button {
            padding: 10px;
            font-size: 1em;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .search-form button:hover, .sort-form button:hover {
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
        .review-header-left {
            display: flex;
            flex-direction: column;
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
        .star-rating {
            color: #FFD700;
            margin-top: 5px;
            font-size: 1.5em;
        }
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .pagination a {
            color: #4CAF50;
            padding: 8px 16px;
            text-decoration: none;
            border: 1px solid #ddd;
            margin: 0 4px;
        }
        .pagination a.active {
            background-color: #4CAF50;
            color: white;
        }
        .pagination a:hover {
            background-color: #ddd;
        }
    </style>
</head>
<body>
<header>
    <h1>User Feedback Search</h1>
</header>
<div class="container">
    <form class="search-form" action="usersearchfeedback" method="get">
        <input type="text" name="keyword" placeholder="Search by Name, Topic" value="${keyword}">
        <button type="submit">Search</button>
    </form>
    <form class="sort-form" action="usersearchfeedback" method="get">
        <input type="hidden" name="keyword" value="${keyword}" />
        <select name="sortBy">
            <option value="created_at" ${sortBy == 'created_at' ? 'selected' : ''}>Created Date</option>
            <option value="rating" ${sortBy == 'rating' ? 'selected' : ''}>Rating</option>
        </select>
        <button type="submit" name="order" value="ASC">Ascending</button>
        <button type="submit" name="order" value="DESC">Descending</button>
        <input type="hidden" name="page" value="${currentPage}" />
        <input type="hidden" name="limit" value="${limit}" />
    </form>
    <form action="LandingPage.jsp" method="get">
        <button type="submit">Home</button>
    </form>
    <form action="userfeedback" method="get">
        <button type="submit">Reset</button>
    </form>
    <div class="feedback-list">
        <c:forEach items="${feedbackList}" var="c">
            <div class="user-review">
                <img src="images/default-avatar.png" alt="User Avatar" width="80" height="80">
                <div class="review-content">
                    <div class="review-header">
                        <div class="review-header-left">
                            <h3>User Name: ${c.username}</h3>
                            <div class="star-rating">
                                <c:forEach begin="1" end="${c.rating}" varStatus="status">
                                    ★
                                </c:forEach>
                                <c:forEach begin="${c.rating + 1}" end="5" varStatus="status">
                                    ☆
                                </c:forEach>
                            </div>
                        </div>
                        <small>Created at: ${c.createdAt}</small>
                    </div>
                    <div class="review-body">
                        <strong>${c.feedbackTopic}</strong>
                        <p>${c.feedbackText}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="usersearchfeedback?page=${currentPage - 1}&limit=${limit}&sortBy=${sortBy}&order=${order}&keyword=${keyword}">Previous</a>
            </c:if>

            <c:set var="beginPage" value="${currentPage > 1 ? currentPage - 1 : 1}" />
            <c:set var="endPage" value="${currentPage < totalPages ? currentPage + 1 : totalPages}" />

            <c:forEach var="i" begin="${beginPage}" end="${endPage}">
                <a href="usersearchfeedback?page=${i}&limit=${limit}&sortBy=${sortBy}&order=${order}&keyword=${keyword}" class="${i == currentPage ? 'active' : ''}">${i}</a>
            </c:forEach>

            <c:if test="${currentPage < totalPages}">
                <a href="usersearchfeedback?page=${currentPage + 1}&limit=${limit}&sortBy=${sortBy}&order=${order}&keyword=${keyword}">Next</a>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>