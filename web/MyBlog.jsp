<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>English Gadget</title>
        <!-- Basic -->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <!-- Site Metas -->
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="icon" href="images/logoTab-01.png" type="images/x-icon">

        <!-- bootstrap core css -->
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
        <!-- font awesome style -->
        <link href="css/font-awesome.min.css" rel="stylesheet" />
        <!-- Custom styles for this template -->
        <link href="css/style.css" rel="stylesheet" />
        <!-- responsive style -->
        <link href="css/responsive.css" rel="stylesheet" />
        <link rel="icon" href="images/logoTab-01.png" type="images/x-icon">
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f8f9fa;
                color: #343a40;
                margin: 0;
                padding: 0;
            }
            .home {
                display: flex;
                justify-content: center;
                background-color: #f8f9fa;
                flex-flow: column;
                width: 100%;
                padding: 20px 0;
            }

            .post {
                margin: 20px auto;
                width: 80%;
                background-color: white;
                border-radius: 12px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                padding: 20px;
                display: flex;
                flex-direction: column;
                transition: box-shadow 0.3s ease;
            }

            .post:hover {
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            }

            .post-highlight {
                border: 2px solid #dc3545;
                box-shadow: 0 4px 8px rgba(220, 53, 69, 0.2);
            }

            .post-border {
                border: 2px solid green;
                box-shadow: 0 4px 8px rgba(220, 53, 69, 0.2);
            }

            .header-post {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
            }

            .post-user {
                margin-right: 15px;
            }

            .avatar {
                font-size: 36px;
                background-color: #007bff;
                color: white;
                width: 50px;
                height: 50px;
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .header-name {
                display: flex;
                flex-direction: column;
            }

            .post-user-name {
                font-weight: bold;
                font-size: 18px;
            }

            .post-time {
                font-size: 12px;
                color: #6c757d;
            }

            .body-post {
                padding: 10px 0;
            }

            .body-post__content {
                font-size: 16px;
                line-height: 1.6;
                color: #495057;
            }

            .body-footer {
                display: flex;
                justify-content: space-around;
                margin-top: 10px;
                color: #6c757d;
            }

            .btn {
                background-color: #007bff;
                color: white;
                padding: 10px 15px;
                border-radius: 5px;
                text-decoration: none;
                transition: background-color 0.3s ease;
            }

            .btn:hover {
                background-color: #0056b3;
            }

            .form-group {
                margin-bottom: 15px;
            }

            select, textarea {
                width: 100%;
                padding: 10px;
                font-size: 16px;
                border-radius: 5px;
                border: 1px solid #ced4da;
                margin-bottom: 10px;
            }

            select:focus, textarea:focus {
                outline: none;
                border-color: #80bdff;
                box-shadow: 0 0 5px rgba(0, 123, 255, 0.25);
            }
        </style>
    </head>
    <body>
        <%@include file="navbarUser.jsp" %>

        <section class="home">
            <c:forEach items="${listPost}" var="post">
                <div class="post ${post.status ? 'post-border' : 'post-highlight'}">
                    <div class="header-post">
                        <div class="post-user">
                            <i class="bi bi-person-circle avatar"></i>
                        </div>
                        <div class="header-name">
                            <div class="post-user-name">${post.customerName}</div>
                            <div class="post-time">
                                ${post.formattedDatePosted}
                                <c:if test="${post.edited}">
                                    <span>(edited)</span>
                                </c:if>
                            </div>
                        </div>
                    </div>

                    <div class="body-post">
                        <form action="myblog?action=update&post=${post.postID}" method="post">
                            <div class="form-group">
                                <label for="category">Category:</label>
                                <select id="category" name="category" required>
                                    <option value="Technology" ${post.category == 'Technology' ? 'selected' : ''}>Technology</option>
                                    <option value="Science" ${post.category == 'Science' ? 'selected' : ''}>Science</option>
                                    <option value="Health" ${post.category == 'Health' ? 'selected' : ''}>Health</option>
                                    <option value="Travel" ${post.category == 'Travel' ? 'selected' : ''}>Travel</option>
                                    <option value="Education" ${post.category == 'Education' ? 'selected' : ''}>Education</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="postText">Post Text:</label>
                                <textarea id="postText" name="postText" rows="4" required>${post.postText}</textarea>
                            </div>

                            <div class="form-group body-footer">
                                <button type="submit" class="btn">Update</button>
                                <a href="myblog?action=delete&post=${post.postID}&id=${post.userID}" class="btn">Delete</a>
                            </div>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </section>
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
