<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Basic -->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="icon" href="images/logoTab-01.png" type="images/x-icon">
        <title>MAGIC GADGET</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
        <link href="css/font-awesome.min.css" rel="stylesheet" />
        <link href="css/style.css" rel="stylesheet" />
        <link href="css/responsive.css" rel="stylesheet" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <style>
            body {
                font-family: Arial, sans-serif; /* Đặt font-family cho toàn bộ trang */
            }
            .suggest_list_container {
                max-height: 400px; /* Chiều cao tối đa khi có nhiều lỗi */
                overflow-y: auto;  /* Hiển thị thanh cuộn dọc */
            }
            .suggest_list {
                padding: 6px ;
                margin: 4px 0;
                border: 1px solid #ddd;
                cursor: pointer; /* Con trỏ thay đổi khi hover để chỉ ra rằng phần tử có thể nhấp */
            }
            .suggest_list:hover {
                background-color: #f0f0f0; /* Thay đổi màu nền khi hover để làm nổi bật phần tử */
            }
            #message {
                margin-top: 20px;
                padding: 10px;
                border: 1px solid #ddd;
                background-color: #f9f9f9;
            }
            .form_input{
                font-size: 16px;
            }
        </style>

        <script>
            // Lấy giá trị từ session 
            var userID = <%= session.getAttribute("userID") != null ? session.getAttribute("userID") : "null" %>;
            var premium = <%= session.getAttribute("premium") != null ? session.getAttribute("premium") : "null" %>;
            var role = <%= session.getAttribute("role") != null ? session.getAttribute("role") : "null" %>;

            console.log("User ID: " + userID);
            console.log("Premium: " + premium);
            console.log("Role: " + role);

            // If admin not check
            if (!role) {
                if (!userID) {
                    window.location.href = "login.jsp";
                } else if (!premium) {
                    window.location.href = "premium.jsp";
                }
            }

            // Gui request
            $(document).ready(function () {
                $('#myForm').submit(function (event) {

                    event.preventDefault();
                    $('#loading').show();
                    $('#loadingText').show();
                    $('#btnCheck').hide();
                    // encode to URL
                    var formData = $(this).serialize();
                    $.ajax({
                        type: 'POST',
                        url: 'GrammarCheck',
                        data: formData,
                        success: function (response) {
                            $('#loading').hide();
                            $('#loadingText').hide();
                            $('#btnCheck').show();
                            // Lay ra correctText dung nhat
                            var correctText = response[response.length - 1].correctText;
                            var correct = "";
                            response.forEach(function (resultCheck, index) {
                                correct += '<div title = "' + resultCheck.message + '" class="suggest_list" data-index="' + index + '">' + resultCheck.errorText + ' ---> ' + resultCheck.listSuggests.join(', ') + '</div>';
                            });
                            if (response.length > 8) {
                                $('#correct').html('<div class="suggest_list_container">' + correct + '</div>');
                            } else {
                                $('#correct').html(correct);
                            }


                            var resultHtml = "";
                            // Correct the text in the textarea
                            resultHtml += '<div class="form-check">';
                            resultHtml += '<div class="card-header bg-success text-white">After Error Correction</div>';
                            resultHtml += '<form id="myForm" action="GrammarCheck" method="post">';
                            resultHtml += '<textarea readonly class="form_input" rows="20" cols="50" placeholder="This is where you get your answers" required>' + correctText + '</textarea>';
                            resultHtml += '</form>';
                            resultHtml += '</div>';
                            $('#results').html(resultHtml);
                        },
                        error: function (error) {
                            console.log('Success response:', error);
                            $('#loading').hide();
                            $('#results').text('Error occurred!');
                        }
                    });
                });
            });
        </script>
    </head>
    <body class="sub_page">
        <div class="hero_area">
            <header class="header_section">
                <div class="container">
                    <nav class="navbar navbar-expand-lg custom_nav-container ">
                        <a class="navbar-brand" href="LandingPage.jsp"><img width="300" src="images/logofixfinal.png" alt="#" /></a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class=""> </span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav">
                                <li class="nav-item">
                                    <a class="nav-link" href="LandingPage.jsp">Home<span class="sr-only">(current)</span></a>
                                </li>
                                <li class="nav-item dropdown active">
                                    <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"> <span class="nav-label">Products<span class="caret"></span></a>

                                    <ul class="dropdown-menu">
                                        <li><a href="GrammarCheck.jsp">Grammar Check (Prime)</a></li>
                                        <li><a href="SpellingCheck.jsp">Spelling Check</a></li>
                                        <li><a href="blog">Blog</a></li>
                                    </ul>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"> <span class="nav-label">Account<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="login.jsp">Sign in</a></li>
                                        <li><a href="signup.jsp">Sign up</a></li>
                                    </ul>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"> <span class="nav-label">About us<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="About.jsp">Development team</a></li>
                                        <li><a href="Contact.jsp">Contact</a></li>
                                    </ul>
                                </li>
                                <form class="form-inline">
                                    <button class="btn  my-2 my-sm-0 nav_search-btn" type="submit">
                                        <i class="fa fa-search" aria-hidden="true"></i>
                                    </button>
                                </form>
                            </ul>
                        </div>
                    </nav>
                </div>
            </header>
            <!-- end header section -->
        </div>
        <!-- inner page section -->
        <section class="inner_page_head">
            <div class="container_fuild">
                <div class="row">
                    <div class="col-md-12">
                        <div class="full">
                            <h3>Checking Grammar</h3>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <br>
        <div class="">
            <div class="row">
                <div class="col-5">
                    <div class="form-check">
                        <div class="card-header bg-success text-white">
                            English Text (US)
                        </div>
                        <form id="myForm" action="GrammarCheck" method="post">
                            <textarea id="text" name="checkText" class="form_input "  rows="20" cols="50" placeholder="Enter or paste your text here to check it......" required ></textarea> 
                            <div class="spinner-border text-success" role="status" id="loading" style="display:none;">
                                <span class="visually-hidden" ></span>
                            </div>
                            <span id="loadingText" style="display:none;">Loading.....</span>
                            <button id="btnCheck" class="btn btn-success">Check Grammar</button>
                        </form>
                    </div>
                </div>

                <div class="col-2 form-result text-center"">
                    <div class="card-header bg-danger text-white">
                        SUGGESTS LIST
                    </div>
                    <div id="correct"></div>
                </div>
                <div id="results" class="col-5"></div>
            </div>
        </div>
        <script src="scripts.js"></script>
        <jsp:include page="Footer.jsp"></jsp:include>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/custom.js"></script>
    </body>
</html>