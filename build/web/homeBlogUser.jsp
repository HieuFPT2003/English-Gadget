<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>English Gadget</title>
        <style>
            .home{
                display: flex;
                justify-content: space-between;
                background-color: #d6d0d0;
                flex-flow: column;
                width: 100%;
            }
            .post{
                margin: 30px 0 50px 0;
                margin-left: auto;
                margin-right:auto;
                padding-top:20px;
                width: 50%;

                display: flex;
                flex-direction: column;
                background-color: white;
                border-radius: 4px;
            }
            
            .header-post{
                display: flex;
                padding-left: 20px;
                margin-bottom: 8px;
                align-items: center;
                position: relative;
            }
            .post-user{
                padding-right: 12px;
            }
            .avatar {
                vertical-align: middle;
                border-radius: 50%;
                font-size: 40px;
            }
            .post-name{
                position: absolute;
                top:15px;
                left:70px;
            }
            .post-user-name{
                font-weight: bold;
                font-size: 20px
            }
            .post-time{
                font-size:12px
            }
            .body-post{
                padding: 10px;
            }
            .body-post__content{
                
            }
            .footer-post{
                display: flex;
                justify-content: space-between;
                margin: 0 !important;
            }
            .emotion{
                color: black;
                font-size: 18px;
                padding: 12px 0;
            }
            .emotion:active{
                background-color: #198754;
                color: white;
                border-radius: 4px;
            }
            .action{
                color:#18d618;
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
                            <div class="post-time">22-5</div>
                        </div>
                    </div>
                    <div class="body-post">
                        <p class="body-post__content">${post.postText}</p>
                    </div>
                    <div class="row footer-post text-center">
                        <div class="col-6 like emotion">
                            <i class="bi bi-hand-thumbs-up-fill action">Like  ${post.likeCount}</i>
                        </div>
                        <div class="col-6 dislike emotion">
                            <i class="bi bi-hand-thumbs-down-fill">Dislike ${post.dislikeCount}</i> 
                        </div>
                    </div>
                </div>
            </c:forEach>
        
    </section>
       <jsp:include page="Footer.jsp"></jsp:include>  
</body>
</html>
