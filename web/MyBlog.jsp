<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>English Gadget</title>
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
        <%@include file="navbarUser.jsp" %>

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
                                <c:if test="${post.edited}">
                                    <span>(edited)</span>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="body-post">
                        <p class="body-post__content">${post.postText}</p>
                    </div>
                    <div class="body-footer">
                        <a href="myblog?action=edit&post=${post.postID}&id=${post.userID}" class="btn">Edit</a>
                        <a href="myblog?action=delete&post=${post.postID}&id=${post.userID}" class="btn">Delete</a>
                    </div>
                </div>
            </c:forEach>
        </section>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>
