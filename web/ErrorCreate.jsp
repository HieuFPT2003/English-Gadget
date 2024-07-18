<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@ page import="model.SimilarPost" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>English Gadget</title>
     <link rel="icon" href="images/logoTab-01.png" type="images/x-icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
        }

        h2 {
            color: #d9534f;
            text-align: center;
        }

        p {
            color: #333;
            font-size: 16px;
            line-height: 1.5;
        }

        .similar-post {
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            padding: 10px;
            border-radius: 4px;
            margin-top: 10px;
        }

        .post-text {
            margin-top: 10px;
            font-style: italic;
        }

        a {
            text-decoration: none;
            color: #198754;
            font-weight: bold;
            margin-top: 20px;
            display: inline-block;
            transition: all 0.3s ease;
        }

        a:hover {
            color: #0d6efd;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Plagiarism Detected</h2>
        <c:if test="${not empty similarPosts}">
            <c:forEach items="${similarPosts}" var="similarPost">
                <div class="similar-post">
                    <p>Similar post found by: ${similarPost.post.customerName}</p>
                    <p>Similarity: ${similarPost.similarity * 100}%</p>
                    <p class="post-text">Post content: ${similarPost.post.postText}</p>
                </div>
            </c:forEach>
        </c:if>
        
        <c:if test="${empty similarPosts}">
            <p>No similar posts found.</p>
        </c:if>
        
        <a href="AdminBlog">Back To Blog</a>
    </div>
</body>
</html>
