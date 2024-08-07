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
            .home {
                display: flex;
                justify-content: center;
                background-color: #f0f0f0;
                flex-flow: column;
                width: 100%;
                padding: 20px 0;
            }

            .post {
                margin: 20px auto;
                width: 70%;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                padding: 20px;
                display: flex;
                flex-direction: column;
            }

            .header-post {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
            }

            .post-user {
                margin-right: 10px;
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
            }

            .footer-post {
                display: flex;
                justify-content: space-between;
                margin-top: 10px;
                color: #6c757d;
            }
        </style>
    </head>
    <body>
        <%@include file="NavbarAdmin.jsp" %>

        <section class="home">
            <c:forEach items="${listPost}" var="post">
                <div class="post">
                    <div class="header-post">
                        <div class="post-user">
                            <i class="bi bi-person-circle avatar"></i>
                        </div>
                        <div class="header-name">
                            <div class="post-user-name">${post.customerName}</div>
                            <div class="post-time">
                                ${post.formattedDatePosted}
                            </div>
                        </div>
                    </div>
                    <div class="body-post">
                        <p class="body-post__content">${post.postText}</p>
                    </div>
                    <div class="body-footer d-flex justify-content-around gap-4">
                        <a href="AdminBlog?action=hidden&postID=${post.postID}" class="btn btn-warning">Hide </a>
                        <a href="AdminBlog?action=delete&postID=${post.postID}" class="btn btn-danger">Delete</a>
                    </div>
                </div>
            </c:forEach>
        </section>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>
