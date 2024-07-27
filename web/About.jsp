<%-- 
    Document   : About
    Created on : May 24, 2024, 3:52:07 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="images/logoTab-01.png" type="images/x-icon">
        <jsp:include page="Header.jsp"></jsp:include>
            <script>
                var userID = <%= session.getAttribute("userID") != null ? session.getAttribute("userID") : "null" %>;
                var premium = <%= session.getAttribute("premium") != null ? session.getAttribute("premium") : "null" %>;
                var role = <%= session.getAttribute("role") != null ? session.getAttribute("role") : "null" %>;
                var name = <%= session.getAttribute("usernamegoogle") != null ? session.getAttribute("usernamegoogle") : "null" %>;

                console.log("User ID: " + userID);
                console.log("Premium: " + premium);
                console.log("Role: " + role);
                console.log("Name: " + name);
        </script>
    </head>
    <body class="sub_page">
        <div class="hero_area">
            <!-- header section strats -->
            <header class="header_section">
                <nav class="navbar navbar-expand-lg custom_nav-container">
                    <a class="navbar-brand" href="home"><img width="300" src="images/logofixfinal.png" alt="#" /></a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class=""> </span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav">
                            <li class="nav-item active">
                                <a class="nav-link" href="home">Home<span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">
                                    <span class="nav-label">Products<span class="caret"></span></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="GrammarCheck.jsp">Grammar Check (Prime)</a></li>
                                    <li><a href="SpellingCheck.jsp">Spelling Check</a></li>
                                    <li><a href="blog">Blog</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <%
                                    HttpSession currentSession = request.getSession();
                                    String username = (String) currentSession.getAttribute("username");
                                    String usernamegoogle = (String) currentSession.getAttribute("usernamegoogle");
    Integer userIdInteger = (Integer) currentSession.getAttribute("userID");
                                    String userid = (userIdInteger != null) ? userIdInteger.toString() : null;

                                    if (userid != null) {
                                        String displayUsername = (username != null) ? username : usernamegoogle;
                                %>
                                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">
                                    <span class="nav-label">Profile<span class="caret"></span></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="profile.jsp">Account detail: <%= displayUsername %></a></li>
                                    <li><a href="">Premium</a></li>
                                    <li><a href="logout">Logout</a></li>
                                </ul>
                                <%
                                    } else {
                                %>
                                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">
                                    <span class="nav-label">Account<span class="caret"></span></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="login.jsp">Sign in</a></li>
                                    <li><a href="signup.jsp">Sign up</a></li>
                                </ul>
                                <%
                                    }
                                %>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">
                                    <span class="nav-label">About us<span class="caret"></span></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="About.jsp">Development team</a></li>
                                    <li><a href="Contact.jsp">Contact</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="help">Help Center<span class="sr-only">(current)</span></a>
                            </li>
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
                    <h3>About us</h3>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- end inner page section -->
<!-- why section -->
<section class="why_section layout_padding">
    <div class="container">
        <div class="heading_container heading_center">
            <h2 style="font-family: Radio Canada Big">
                Development Team English Gadget
            </h2>
        </div>
        <br>
        <!-- ***** Our Team Area Starts ***** -->
        <section class="our-team">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="team-item">
                            <div class="thumb">
                                <img src="images/image5.jpg" style="width: 280px">
                            </div>
                            <br>
                            <div class="down-content">
                                <h4>Nguyễn Quang Hiếu</h4>
                                <span>Leader</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="team-item">
                            <div class="thumb">

                                <img src="images/image1.jpg" style="width: 280px">
                            </div>
                            <br>
                            <div class="down-content">
                                <h4>Đặng Đình Minh</h4>
                                <span>Member</span>
                            </div>
                        </div>
                        <div>
                            <br>
                            <br>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="team-item">
                            <div class="thumb">
                                <img src="images/image.jpg"  style="width: 280px">
                            </div>
                            <br>
                            <div class="down-content">
                                <h4>Đoàn Duy Khánh</h4>
                                <span>Member</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="team-item">
                            <div class="thumb">
                                <img src="images/image4.jpg"  style="width: 280px">
                            </div>
                            <br>
                            <div class="down-content">
                                <h4>Trần Khánh Tùng</h4>
                                <span>Member</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="team-item">
                            <div class="thumb">
                                <img src="images/image6.jpg"   style="width: 280px">
                            </div>
                            <br>
                            <div class="down-content">
                                <h4>Grammarly</h4>
                                <span>Sponsor</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="team-item">
                            <div class="thumb">
                                <img src="images/chatgpt.jpg"  style="width: 280px">
                            </div>
                            <br>
                            <div class="down-content">
                                <h4>Chat GPT</h4>
                                <span>Sponsor</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- ***** Our Team Area Ends ***** -->

</section>
<!-- end why section -->
<!-- arrival section -->
<section class="arrival_section">
    <div class="container">
        <div class="box">
            <div class="arrival_bg_box">
                <img src="images/arrival-bg.png" alt="">
            </div>
            <div class="row">
                <div class="col-md-6 ml-auto">
                    <div class="heading_container remove_line_bt">
                        <h2>
                            #Coming soon
                        </h2>
                    </div>
                    <p style="margin-top: 20px;margin-bottom: 30px;">
                        Get ready for the launch of our groundbreaking translation and blog website! <br>

                        🌟 Accurate and Fast Translations: Our advanced technology and professional translators ensure precise and smooth translations for any content you need. <br>

                        🌟 Rich and Useful Bl qog: Discover a wealth of knowledge with quality blog posts on language learning tips, cultural insights, and inspiring stories. <br>

                        🌟 User-Friendly Interface: Our website is designed for an effortless user experience, making it easy to access services with just a few clicks. <br>

                        🌟 Special Launch Offers: Enjoy exclusive deals for early users when you sign up and use our services at launch.                                </p>
                    <a href="">
                        Sign up Now
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>
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