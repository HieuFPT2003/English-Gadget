<%-- 
    Document   : Contact
    Created on : May 24, 2024, 3:52:42 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Basic -->
        <jsp:include page="Header.jsp"></jsp:include>
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
                                    <li class="nav-item dropdown  active">
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
            </div>
            <!-- inner page section -->
            <section class="inner_page_head">
                <div class="container_fuild">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="full">
                                <h3>Contact us</h3>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- end inner page section -->
            <!-- why section -->
            <section class="why_section layout_padding">
                <div class="container">

                    <div class="row">
                        <div class="col-lg-8 offset-lg-2">
                            <div class="full">
                                <form action="index.html">
                                    <fieldset>
                                        <input type="text" placeholder="Enter your full name" name="name" required />
                                        <input type="email" placeholder="Enter your email address" name="email" required />
                                        <input type="text" placeholder="Enter subject" name="subject" required />
                                        <textarea placeholder="Enter your message" required></textarea>
                                        <input type="submit" value="Submit" />
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- end why section -->
            <!-- arrival section -->
            <!-- end arrival section -->
            <!-- footer section -->
        <jsp:include page="Footer.jsp"></jsp:include>
        <!-- footer section -->
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