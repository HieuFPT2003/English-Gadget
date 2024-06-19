<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="Header.jsp" />
    </head>
    <body>
        <div class="hero_area">
            <!-- header section starts -->
            <header class="header_section">
                <div class="container">
                    <nav class="navbar navbar-expand-lg custom_nav-container">
                        <a class="navbar-brand" href="LandingPage.jsp"><img width="300" src="images/logofixfinal.png" alt="#" /></a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class=""> </span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">
                                        <span class="nav-label">Account<span class="caret"></span></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="login.jsp">Logout</a></li>
                                    </ul>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">
                                        <span class="nav-label">Manage<span class="caret"></span></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="list">Manage Account</a></li>
                                        <li><a href="userfeedback">Manage Feedback</a></li>
                                    </ul>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">
                                        <span class="nav-label">About us<span class="caret"></span></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="About.jsp">Development team</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </header>
            <!-- end header section -->
            <!-- slider section -->
            <section class="slider_section">
                <div class="slider_bg_box">
                    <video width="100%" autoplay>
                        <source src="videos/banner.mp4" type="video/mp4">
                        <source src="movie.ogg" type="video/ogg">
                        Your browser does not support the video tag.
                    </video>
                </div>
                <div id="customCarousel1" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-7 col-lg-6">
                                        <div class="detail-box">
                                            <h1 style="font-family: Radio Canada Big">
                                                <span>Just copy </span><br>and paste
                                            </h1>
                                            <p>
                                                Are you often worried about making spelling and grammar mistakes in your writing? Let us help you! With our advanced spelling and grammar check website, you no longer have to worry about those minor errors.
                                            </p>
                                            <div class="btn-box">
                                                <a href="" class="btn1">Sign up now!</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-7 col-lg-6">
                                        <div class="detail-box">
                                            <h1 style="font-family: Radio Canada Big">
                                                <span>Don’t let </span><br>small spelling
                                            </h1>
                                            <p>
                                                and grammar mistakes diminish a good impression. Our spelling and grammar check website uses advanced AI technology to help you detect and correct errors accurately and quickly.
                                            </p>
                                            <div class="btn-box">
                                                <a href="" class="btn1">Sign up now!</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-7 col-lg-6">
                                        <div class="detail-box">
                                            <h1 style="font-family: Radio Canada Big">
                                                <span>Whether you </span><br>are writing 
                                            </h1>
                                            <p>
                                                essays, work emails, or social media posts, our tool is always ready to assist, ensuring that every word you use is accurate and professional. Try it today and see the difference!
                                            </p>
                                            <div class="btn-box">
                                                <a href="" class="btn1">Sign up now!</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="container">
                        <ol class="carousel-indicators">
                            <li data-target="#customCarousel1" data-slide-to="0" class="active"></li>
                            <li data-target="#customCarousel1" data-slide-to="1"></li>
                            <li data-target="#customCarousel1" data-slide-to="2"></li>
                        </ol>
                    </div>
                </div>
            </section>
            <!-- end slider section -->
        </div>
        <!-- footer start -->
        <jsp:include page="Footer.jsp" />
        <!-- footer end -->

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
