<%-- 
    Document   : HelpCenter
    Created on : Jun 17, 2024, 12:35:04 PM
    Author     : Admin
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <jsp:include page="Header.jsp"></jsp:include>

            <style>
                .search-bar {
                    margin: 40px 0;
                }
                .popular-topics {
                    margin-top: 30px;
                }
                .topic-card {
                    background-color: #f8f8f8;
                    border: none;
                    border-radius: 10px;
                    padding: 20px;
                    text-align: center;
                    height: 100%;
                }
                .topic-card img {
                    width: 80px;
                    height: 80px;
                    margin-bottom: 20px;
                    display: block;
                    margin-left: auto;
                    margin-right: auto;
                }
                .topic-title {
                    font-size: 1.2rem;
                    font-weight: bold;
                }
                .topic-description {
                    font-size: 0.9rem;
                    color: #b0b0b0;
                }
            </style>

        </head>
        <body class="sub_page">
            <div class="hero_area">
                <!-- header section strats -->
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
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"> <span class="nav-label">Products<span class="caret"></span></a>

                                        <ul class="dropdown-menu">
                                            <li><a href="Check_Backup.jsp">Grammar Check</a></li>
                                            <li><a href="Blog.jsp">Blog</a></li>
                                        </ul>

                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"> <span class="nav-label">Account<span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="product.html">Sign in</a></li>
                                            <li><a href="contact.html">Sign up</a></li>
                                        </ul>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"> <span class="nav-label">About us<span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="About.jsp">Development team</a></li>
                                            <li><a href="Contact.jsp">Contact</a></li>
                                        </ul>
                                    </li>
                                    <li class="nav-item  active">
                                        <a class="nav-link" href="help">Help Center<span class="sr-only"></span></a>
                                    </li>

                                    <form class="form-inline">

                                        <!--                        <input type="text" name="search"placeholder="Tìm kiếm">-->
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
                                <h3>Help Center</h3>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <br>
            <div class="container text-center">
                <form action="searchqa" >
                    <fieldset style = "justify-content: center">
                        <legend>Search for problems?</legend>
                        <div class="inner-form">
                            <div class="input-field">
                                <input class="form-control" id="choices-text-preset-values" name = "txt" type="text" placeholder="Type to search..." />
                                <button class="btn-search" type="submit" name ="txt">Search
                                    <svg  width="24" height="24" viewBox="0 0 24 24">
                                    <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"></path>
                                    </svg>
                                </button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="container text-center">
                <br>
                <div class="row">
                <c:forEach items="${listTopic}" var="o">
                    <div class="col-md-3 mb-4">
                        <div class="card topic-card">
                            <img src="${o.topicPics}" />
                            <div class="topic-title">${o.topicName}</div>
                            <div class="topic-description">${o.topicDetail}</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <br> 
        <section class="inner_page_head">
            <div class="container_fuild">
                <div class="row">
                    <div class="col-md-12">
                        <div class="full">
                            <h4>Question and Answer</h4>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <br>

        <div class="container text-center">
            <div class="container">
                <div class="row">
                    <!-- Đảm bảo rằng `listTopic` có giá trị -->
                    <c:if test="${not empty ms}">
                        <div class="alert alert-warning mt-3">${ms}</div>
                    </c:if>
                    <c:forEach items="${listQaA}" var="o1">
                        <div class="col-md-6 d-flex align-items-center justify-content-center">
                            <div class="dropdown">
                                <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                    ${o1.questionContent}
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <li><a class="dropdown-item">&ensp; ${o1.answerContent} &ensp;</a></li>
                                </ul>
                            </div>
                        </div>
                        <br>
                        <br>
                    </c:forEach>
                </div>
            </div>
        </div>
        <br>
        <jsp:include page="Footer.jsp"></jsp:include>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>


    </body>

</html>
