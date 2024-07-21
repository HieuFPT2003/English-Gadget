<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Boolean role = (Boolean) session.getAttribute("role");
    if (role == null || !role) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="Header.jsp" />
    </head>
    <script>
        var userID = <%= session.getAttribute("userID") != null ? "\"" + session.getAttribute("userID") + "\"" : "null" %>;
        var premium = <%= session.getAttribute("premium") != null ? "\"" + session.getAttribute("premium") + "\"" : "null" %>;
        var role = <%= session.getAttribute("role") != null ? "\"" + session.getAttribute("role") + "\"" : "null" %>;
        var name = <%= session.getAttribute("usernamegoogle") != null ? "\"" + session.getAttribute("usernamegoogle") + "\"" : "null" %>;

        console.log("User ID: " + userID);
        console.log("Premium: " + premium);
        console.log("Role: " + role);
        console.log("Name: " + name);
    </script>
<head>
    <meta charset="UTF-8">
    <title>List of Feedback</title>
    <script type="text/javascript">
        function doDelete(id) {
            if (confirm("Are you sure you want to delete feedback with ID=" + id + "?")) {
                window.location = "deletefeedback?feedbackID=" + id;
            }
        }

        function doApprove(id) {
            if (confirm("Are you sure you want to approve feedback with ID=" + id + "?")) {
                window.location = "approvefeedback?feedbackID=" + id;
            }
        }
    </script>
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
            position: relative;
        }
        h1 {
            margin: 0;
            font-size: 2em;
        }
        .home-button {
            position: absolute;
            top: 50%;
            left: 20px;
            transform: translateY(-50%);
        }
        .home-button button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
        }
        .home-button button:hover {
            background-color: #45a049;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: flex;
        }
        .sidebar {
            width: 25%;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-right: 20px;
        }
        .content {
            width: 70%;
        }
        .filter-group {
            margin-bottom: 20px;
        }
        .filter-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .filter-group input[type="text"], .filter-group select, .filter-group button {
            width: 100%;
            padding: 10px;
            font-size: 1em;
            margin-bottom: 10px;
        }
        .filter-group button {
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .filter-group button:hover {
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
            margin: 0 5px;
        }
        td a:hover {
            color: #45a049;
        }
        .rating {
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.5em;
            color: #FFD700;
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
    <h1>List of Feedback</h1>
    <div class="home-button">
        <form action="AdminLandingPage.jsp" method="get">
            <button type="submit">Home</button>
        </form>
    </div>
</header>
<div class="container">
    <div class="sidebar">
        <h2>Filters</h2>
        <form id="filterForm" action="searchfeedback" method="get">
            <!-- Filter by Keyword -->
            <div class="filter-group">
                <input type="text" name="keyword" placeholder="Search by keyword" value="${keyword}">
            </div>
            <!-- Filter by Status -->
            <div class="filter-group">
                <label for="status">Status</label>
                <select name="status" id="status">
                    <option value="">All</option>
                    <option value="0" ${status == '0' ? 'selected' : ''}>Browsing</option>
                    <option value="1" ${status == '1' ? 'selected' : ''}>Approved</option>
                </select>
            </div>
            <!-- Filter by Rating -->
            <div class="filter-group">
                <label for="rating">Rating</label>
                <select name="rating" id="rating">
                    <option value="">All</option>
                    <option value="5" ${rating == '5' ? 'selected' : ''}>★★★★★</option>
                    <option value="4" ${rating == '4' ? 'selected' : ''}>★★★★☆</option>
                    <option value="3" ${rating == '3' ? 'selected' : ''}>★★★☆☆</option>
                    <option value="2" ${rating == '2' ? 'selected' : ''}>★★☆☆☆</option>
                    <option value="1" ${rating == '1' ? 'selected' : ''}>★☆☆☆☆</option>
                </select>
            </div>
            <!-- Sort by Date -->
            <div class="filter-group">
                <label for="order">Sort by Date</label>
                <select name="order" id="order">
                    <option value="">All</option>
                    <option value="ASC" ${order == 'ASC' ? 'selected' : ''}>Ascending</option>
                    <option value="DESC" ${order == 'DESC' ? 'selected' : ''}>Descending</option>
                </select>
            </div>
            <!-- Apply Filters -->
            <button type="submit">Apply Filters</button>
        </form>
    </div>
    <div class="content">
        <!-- Feedback table -->
        <table>
            <thead>
                <tr>
                    <th>Feedback ID</th>
                    <th>User Name</th>
                    <th>Feedback Topic</th>
                    <th>Feedback Text</th>
                    <th>Rating</th>
                    <th>Created At</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${feedbackList}" var="f">
                    <tr>
                        <td>${f.feedbackID}</td>
                        <td>${f.username}</td>
                        <td>${f.feedbackTopic}</td>
                        <td>${f.feedbackText}</td>
                        <td>
                            <div class="rating">
                                <c:forEach begin="1" end="${f.rating}" var="star">
                                    &#9733;
                                </c:forEach>
                                <c:forEach begin="${f.rating + 1}" end="5" var="star">
                                    &#9734;
                                </c:forEach>
                            </div>
                        </td>
                        <td>${f.createdAt}</td>
                        <td>${f.status == 1 ? 'Approved' : 'Browsing'}</td>
                        <td>
                            <a href="#" onclick="doDelete('${f.feedbackID}')">Delete</a>
                            <a href="#" onclick="doApprove('${f.feedbackID}')">Approve</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
       <!-- Pagination -->
<div class="pagination">
    <c:if test="${currentPage > 1}">
        <a href="managefeedback?page=${currentPage - 1}&sortBy=${sortBy}&order=${order}&keyword=${keyword}&status=${status}&rating=${rating}">Previous</a>
    </c:if>
    <c:set var="beginPage" value="${currentPage > 1 ? currentPage - 1 : 1}" />
    <c:set var="endPage" value="${currentPage < totalPages - 1 ? currentPage + 1 : totalPages}" />
    <c:forEach var="i" begin="${beginPage}" end="${endPage}">
        <a href="managefeedback?page=${i}&sortBy=${sortBy}&order=${order}&keyword=${keyword}&status=${status}&rating=${rating}" class="${i == currentPage ? 'active' : ''}">${i}</a>
    </c:forEach>
    <c:if test="${currentPage < totalPages}">
        <a href="managefeedback?page=${currentPage + 1}&sortBy=${sortBy}&order=${order}&keyword=${keyword}&status=${status}&rating=${rating}">Next</a>
    </c:if>
</div>

        <!-- Success message for approval -->
        <c:if test="${not empty message}">
            <div style="color: green; text-align: center; margin-top: 20px;">
                ${message}
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
