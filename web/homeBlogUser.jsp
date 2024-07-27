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
                position: relative; /* Thêm dòng này */
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
            .header-post-right {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
            }
            .report {
                position: absolute; /* Thêm dòng này */
                top: 10px; /* Khoảng cách từ phía trên */
                right: 10px; /* Khoảng cách từ phía phải */
                cursor: pointer;
                color: #6c757d;
            }

            .report:hover {
                color: #007bff;
            }

            .dropdown-menu {
                position: absolute;
                top: 100%;
                left: auto;
                right: 0;
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

            .emotion {
                cursor: pointer;
                display: flex;
                align-items: center;
                font-size: 24px;
                justify-content: center; /* Thêm dòng này để căn giữa nội dung */
                padding: 12px 0;
            }

            .emotion:hover {
                color: #007bff;
            }


            /*            .action {
                            font-size: 14px;
                            margin-right: 10px;
                        }*/

        </style>
    </head>
    <body>
        <%@include file="navbarUser.jsp" %>
        <section class="home">
            <div class="col-lg-8 offset-lg-2">
                <c:if test="${not empty logsu}">
                    <div class="alert alert-success mt-3" style="text-align: center">${logsu}</div>
                    <div>
                        <a class="btn btn-light" style="text-align: center"  href="blog" role="button">Get back to Blog</a>
                    </div>
                </c:if>
                <c:if test="${not empty logfa}}">
                    <div class="alert alert-danger mt-3"  style="text-align: center">${logfa}</div>
                    <a class="btn btn-light" style="text-align: center" href="blog" role="button">Get back to Blog</a>
                </c:if>

            </div>
            <c:forEach items="${listPost}" var="post"> 
                <div class="post">
                    <div class="report" >
                        <i class="bi bi-flag-fill " type="button" data-bs-toggle="dropdown" aria-expanded="false"></i>
                        <ul class="dropdown-menu">
                            <form action="ReportPostServlet" method="post">
                                <li>
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <label for="reportType" style="justify-content: center">Choose a report type:</label>
                                            <select id="reportType" name="reportType" required>
                                                <option value="" disabled selected hidden>Select a type</option>
                                                <option value="Spam">Spam</option>
                                                <option value="Inappropriate">Inappropriate Content</option>
                                                <option value="False Information">False Information</option>
                                                <option value="Harassment">Harassment</option>
                                                <option value="Privacy Violation">Privacy Violation</option>
                                                <option value="Copyright Violation">Copyright Violation</option>
                                                <option value="Hate Speech">Hate Speech</option>
                                                <option value="Community Guidelines Violation">Community Guidelines Violation</option>
                                                <option value="Financial Scam">Financial Scam</option>
                                                <option value="Insulting Communist Party">Insulting the Communist Party of Vietnam</option>
                                                <option value="Other">Other</option>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="message-text" class="col-form-label">Message:</label>
                                            <textarea class="form-control" name="message" placeholder="Enter message"></textarea>
                                        </div>
                                    </div>
                                </li>
                                <li><textarea class="form-control" name="postID" readonly hidden>${post.postID}</textarea></li>
                                <li><textarea class="form-control" name="userID" readonly hidden>${post.userID}</textarea></li>
                                <li><textarea class="form-control" name="userReportID" readonly hidden>${sessionScope.userID}</textarea></li>
                                <li><textarea class="form-control" name="userReportName" readonly hidden>${sessionScope.username}</textarea></li>
                                <li><button type="submit" class="btn btn-danger">Send report</button></li>
                            </form>
                        </ul>
                    </div>
                    <div class="header-post">
                        <div class="post-user">
                            <i class="bi bi-person-circle avatar"></i>
                        </div>
                    </div>
                    <div class="body-post">
                        <p class="body-post__content">${post.postText}</p>
                    </div>
                </div>
            </c:forEach>
        </section>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <jsp:include page="Footer.jsp"></jsp:include>  
    </body>
</html>
