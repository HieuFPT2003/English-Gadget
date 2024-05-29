<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="Header.jsp"></jsp:include>
    </head>
    <body>
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
                                <li class="nav-item active">
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
            <!-- slider section -->
            <section class="slider_section ">
                <div class="slider_bg_box">

                    <video width="1405" height="631" autoplay>
                        <source src="videos/banner.mp4" type="video/mp4">
                        <source src="movie.ogg" type="video/ogg">

                        Your browser does not support the video tag.
                    </video>
                    <!--                        <img src="images/slider-bg.jpg" alt="">-->
                </div>
                <div id="customCarousel1" class="carousel slide" data-ride="carousel" >
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <div class="container ">
                                <div class="row">
                                    <div class="col-md-7 col-lg-6 ">
                                        <div class="detail-box">
                                            <h1 style="font-family: Radio Canada Big">
                                                <span>
                                                    Just copy 
                                                </span>
                                                <br>
                                                and paste
                                            </h1>
                                            <p>
                                                Are you often worried about making spelling and grammar mistakes in your writing? Let us help you! With our advanced spelling and grammar check website, you no longer have to worry about those minor errors.                                                </p>
                                            <div class="btn-box">
                                                <a href="" class="btn1">
                                                    Sign up now!
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item ">
                            <div class="container ">
                                <div class="row">
                                    <div class="col-md-7 col-lg-6 ">
                                        <div class="detail-box">
                                            <h1  style="font-family: Radio Canada Big">
                                                <span>
                                                    Don’t let 
                                                </span>
                                                <br>
                                                small spelling
                                            </h1>
                                            <p>
                                                and grammar mistakes diminish a good impression. Our spelling and grammar check website uses advanced AI technology to help you detect and correct errors accurately and quickly.                                                <div class="btn-box">
                                                <a href="" class="btn1">
                                                    Sign up now!
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <div class="container ">
                                <div class="row">
                                    <div class="col-md-7 col-lg-6 ">
                                        <div class="detail-box">
                                            <h1  style="font-family: Radio Canada Big">
                                                <span>
                                                    Whether you 
                                                </span>
                                                <br>
                                                are writing 
                                            </h1>
                                            <p>
                                                essays, work emails,  or social media posts Our tool is always ready to assist, ensuring that every word you use is accurate and professional. Try it today and see the difference!                                                </p>
                                            <div class="btn-box">
                                                <a href="" class="btn1">
                                                    Sign up now!
                                                </a>
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
        <!-- why section -->

        <!-- end why section -->

        <!-- arrival section -->

        <!-- end arrival section -->

        <!-- product section -->

        <!-- end product section -->

        <!-- subscribe section -->

        <!-- end subscribe section -->
        <!-- client section -->
        <section class="client_section layout_padding">
            <div class="container">
                <div class="heading_container heading_center">
                    <h2  style="font-family: Radio Canada Big">
                        Customer's Testimonial
                    </h2>
                </div>
                <div id="carouselExample3Controls" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <div class="box col-lg-10 mx-auto">
                                <div class="img_container">
                                    <div class="img-box">
                                        <div class="img_box-inner">
                                            <img src="images/client.jpg" alt="">
                                        </div>
                                    </div>
                                </div>
                                <div class="detail-box">
                                    <h5>
                                        Anna Trevor
                                    </h5>
                                    <h6>
                                        Customer
                                    </h6>
                                    <p>
                                        Dignissimos reprehenderit repellendus nobis error quibusdam? Atque animi sint unde quis reprehenderit, et, perspiciatis, debitis totam est deserunt eius officiis ipsum ducimus ad labore modi voluptatibus accusantium sapiente nam! Quaerat.
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <div class="box col-lg-10 mx-auto">
                                <div class="img_container">
                                    <div class="img-box">
                                        <div class="img_box-inner">
                                            <img src="images/client.jpg" alt="">
                                        </div>
                                    </div>
                                </div>
                                <div class="detail-box">
                                    <h5>
                                        Anna Trevor
                                    </h5>
                                    <h6>
                                        Customer
                                    </h6>
                                    <p>
                                        Dignissimos reprehenderit repellendus nobis error quibusdam? Atque animi sint unde quis reprehenderit, et, perspiciatis, debitis totam est deserunt eius officiis ipsum ducimus ad labore modi voluptatibus accusantium sapiente nam! Quaerat.
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <div class="box col-lg-10 mx-auto">
                                <div class="img_container">
                                    <div class="img-box">
                                        <div class="img_box-inner">
                                            <img src="images/client.jpg" alt="">
                                        </div>
                                    </div>
                                </div>
                                <div class="detail-box">
                                    <h5>
                                        Anna Trevor
                                    </h5>
                                    <h6>
                                        Customer
                                    </h6>
                                    <p>
                                        Dignissimos reprehenderit repellendus nobis error quibusdam? Atque animi sint unde quis reprehenderit, et, perspiciatis, debitis totam est deserunt eius officiis ipsum ducimus ad labore modi voluptatibus accusantium sapiente nam! Quaerat.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="carousel_btn_box">
                        <a class="carousel-control-prev" href="#carouselExample3Controls" role="button" data-slide="prev">
                            <i class="fa fa-long-arrow-left" aria-hidden="true"></i>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExample3Controls" role="button" data-slide="next">
                            <i class="fa fa-long-arrow-right" aria-hidden="true"></i>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div>
        </section>
        <!-- end client section -->
        <!-- footer start -->
        <jsp:include page="Footer.jsp"></jsp:include>
        <!-- footer end -->
        
        <!-- jQery -->
        <script src="js/jquery-3.4.1.min.js"></script>
        <!-- popper js -->
        <script src="js/popper.min.js"></script>
        <!-- bootstrap js -->
        <script src="js/bootstrap.js"></script>
        <!-- custom js -->
        <script src="js/custom.js"></script>
    </body>
</html>
