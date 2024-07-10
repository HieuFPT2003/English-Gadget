<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <link rel="icon" href="images/logoTab-01.png" type="images/x-icon">
    <title>English Gadget</title>
    <style>
        .edit-post-form {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .form-group input[type="text"], .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form-group button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .form-group button:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: red;
            font-size: 14px;
            margin-top: 5px;
        }
    </style>
    <script>
        function validateForm() {
            var postText = document.getElementById('postText').value.trim();
            var errorMessage = document.getElementById('errorMessage');

            if (postText === '') {
                errorMessage.textContent = 'Post text cannot be empty.';
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<%@include file="navbarUser.jsp" %>
<div class="edit-post-form">
    <h2>Edit Post</h2>
    <form action="myblog" method="post" onsubmit="return validateForm()">
        <input type="hidden" name="postID" value="${post.postID}">
        <input type="hidden" name="userID" value="${post.userID}">

        <div class="form-group">
            <label for="customerName">Customer Name:</label>
            <input type="text" id="customerName" name="customerName" value="${post.customerName}" readonly>
        </div>

        <div class="form-group">
            <label for="datePosted">Date Posted:</label>
            <input type="text" id="datePosted" name="datePosted" value="${post.formattedDatePosted}" readonly>
        </div>

        <div class="form-group">
            <label for="postText">Post Text:</label>
            <textarea id="postText" name="postText" rows="6">${post.postText}</textarea>
            <div id="errorMessage" class="error-message"></div>
        </div>

        <div class="form-group">
            <button type="submit">Update Post</button>
        </div>
    </form>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
