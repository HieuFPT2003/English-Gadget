<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" href="images/logoTab-01.png" type="images/x-icon">
    <title>English Gadget</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        
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

            .btn-accept {
                background-color: #28a745;
            }

            .btn-accept:hover {
                background-color: #218838;
            }

            .btn-check {
                background-color: #ffc107;
                color: #343a40;
            }

            .btn-check:hover {
                background-color: #e0a800;
            }

            .btn-delete {
                background-color: #dc3545;
            }

            .btn-delete:hover {
                background-color: #c82333;
            }
        </style>
    </head>
    <body>
        <%@include file="NavbarAdmin.jsp" %>
        <section class="home">
            <h3>Admin Manage Blog</h3>

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
                    <div class="body-footer">
                          <a href="#" class="btn btn-accept" onclick="acceptPost(${post.postID})">Accepted</a>
                        <a href="AdminBlog?action=check&postID=${post.postID}" class="btn">Plagiarism Check</a>
                        <a href="AdminBlog?action=delete&postID=${post.postID}" class="btn btn-delete">Delete</a>
                    </div>
                </div>
            </c:forEach>
        </section>
      <script>
        function acceptPost(postID) {
            swal({
                title: "Are you sure?",
                text: "Once accepted, you will not be able to revert this action!",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })
            .then((willAccept) => {
                if (willAccept) {
                    window.location.href = 'AdminBlog?action=accept&postID='+ postID;
                } else {
                    swal("The post is not accepted!");
                }
            });
        }
    </script>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>
