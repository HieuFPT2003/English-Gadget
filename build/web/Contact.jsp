<%-- 
    Document   : Contact
    Created on : May 24, 2024, 3:52:42 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Basic -->
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
            <div class="col-lg-8 offset-lg-2">
            <c:if test="${not empty logsu}">
                <div class="alert alert-success mt-3" style="text-align: center">${logsu}</div>
            </c:if>
            <c:if test="${not empty logfa}}">
                <div class="alert alert-danger mt-3"  style="text-align: center">${logfa}</div>
            </c:if>
        </div>
        <section class="why_section layout_padding" style="padding-top: 35px">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 offset-lg-2">
                        <div class="full">
                            <form action="EmailContactServlet" method = post>
                                <fieldset>  
                                    <input type="text" placeholder="Enter name" name="name" required />
                                    <input type="email" placeholder="Enter email" name="email" required />
                                    Choose related subject: <select name="subject" class="input-text" required>
                                        <option class="dropdown-item" value="" disabled selected>Select a subject</option>
                                        <option class="dropdown-item" value="General Inquiry">General Inquiry</option>
                                        <option class="dropdown-item" value="Report issue">Report issue</option>
                                        <option class="dropdown-item" value="Support Request">Support Request</option>
                                        <option class="dropdown-item" value="Billing Issues">Billing Issues</option>
                                        <option class="dropdown-item" value="Feature Request">Feature Request</option>
                                        <option class="dropdown-item" value="Technical Support">Technical Support</option>
                                        <option class="dropdown-item" value="Account Issues">Account Issues</option>
<option class="dropdown-item" value="Partnership Inquiry">Partnership Inquiry</option>
                                    </select>
                                    <br>
                                    <br>

                                    <textarea id="message" name="message" placeholder="Enter your message" required></textarea>
                                    <input type="submit" value="Submit"/>
                                </fieldset>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </section>
        <!------ Include the above in your HEAD tag ---------->

        <!--            <section class="client_section layout_padding">
                        <div class="container">
                            <div class="heading_container heading_center">
                                <h2  style="font-family: Radio Canada Big">
                                    Customer's Testimonial
                                </h2>
                            </div>
                            <div id="carouselExample3Controls" class="carousel slide" data-ride="carousel">
                                <div class="carousel-inner">
        <c:forEach items="${list}" var ="o">
            <div class="carousel-item active">
                <div class="box col-lg-10 mx-auto">
                    <div class="img_container">
                        <div class="img-box">
                            <div class="img_box-inner">
                                <img src="${o.userImage}" alt="">
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
            ${o.feedbackText};
        </p>
    </div>
</div>
</div>
        </c:forEach>
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
</section>-->
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