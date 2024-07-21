<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>English Gadget</title>
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
        // Redirect to AdminLandingPage.jsp if role is true
            if (role === "true") {
                window.location.href = 'AdminLandingPage.jsp';
            }
        </script>
    </head>
    <body>
        <header class="header_section">
            <div class="container">
                <nav class="navbar navbar-expand-lg custom_nav-container">
                    <a class="navbar-brand" href="LandingPage.jsp"><img width="300" src="images/logofixfinal.png" alt="#" /></a>
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
                                    <li><a href="userprofile">Account detail: <%= displayUsername %></a></li>
                                    <li><a href="premium.jsp">Premium</a></li>
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
                            <form class="form-inline" action="Search.jsp">
                                <button class="btn my-2 my-sm-0 nav_search-btn" type="submit">
                                    <i class="fa fa-search" aria-hidden="true"></i>
                                </button>
                            </form>
                        </ul>
                    </div>
                </nav>
            </div>
        </header>

        <section class="slider_section">
            <div class="slider_bg_box">
                <video width="rem" autoplay>
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
                                        <h1 style="font-family: 'Radio Canada Big';">
                                            <span>Just copy</span><br>and paste
                                        </h1>
                                        <p>
                                            Are you often worried about making spelling and grammar mistakes in your writing? Let us help you! With our advanced spelling and grammar check website, you no longer have to worry about those minor errors.
                                        </p>
                                        <div class="btn-box">
                                            <a href="signup.jsp" class="btn1">Sign up now!</a>
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
                                        <h1 style="font-family: 'Radio Canada Big';">
                                            <span>Don’t let</span><br>small spelling
                                        </h1>
                                        <p>
                                            and grammar mistakes diminish a good impression. Our spelling and grammar check website uses advanced AI technology to help you detect and correct errors accurately and quickly.
                                        </p>
                                        <div class="btn-box">
                                            <a href="signup.jsp" class="btn1">Sign up now!</a>
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
                                        <h1 style="font-family: 'Radio Canada Big';">
                                            <span>Whether you</span><br>are writing
                                        </h1>
                                        <p>
                                            essays, work emails, or social media posts. Our tool is always ready to assist, ensuring that every word you use is accurate and professional. Try it today and see the difference!
                                        </p>
                                        <div class="btn-box">
                                            <a href="signup.jsp" class="btn1">Sign up now!</a>
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
                <div>
                    <br>
                    <br>
                </div>
            </div>
        </section>

        <section class="why_section layout_padding">
            <div class="container">
                <div class="heading_container heading_center">
                    <h2 style="font-family: Radio Canada Big">
                        Hot sale for our users!
                    </h2>
                </div>
                <br>
                <c:if test="${empty sessionScope.id or sessionScope.premium == 0}">
                <div id="adsCarousel" class="carousel slide" data-ride="carousel" data-interval="3000">
                    <div class="carousel-inner">
                        <c:forEach var="ad" items="${showAds}" varStatus="status">
                            <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                <img src="${ad.imageAd}" class="d-block w-100" style="height: 400px; object-fit: cover;" alt="${ad.title}">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5>${ad.title}</h5>
                                    <p>${ad.description}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <a class="carousel-control-prev" href="#adsCarousel" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#adsCarousel" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </c:if>
            </div>
        </section>

        <jsp:include page="Footer.jsp"></jsp:include>

        <!-- jQuery -->
        <script src="js/jquery-3.4.1.min.js"></script>
        <!-- Popper JS -->
        <script src="js/popper.min.js"></script>
        <!-- Bootstrap JS -->
        <script src="js/bootstrap.js"></script>
        <!-- Custom JS -->
        <script src="js/custom.js"></script>
        <script src="script.js"></script>
    </body>
</html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>English Gadget</title>
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
        // Redirect to AdminLandingPage.jsp if role is true
            if (role === "true") {
                window.location.href = 'AdminLandingPage.jsp';
            }
        </script>
    </head>
    <body>
        <header class="header_section">
            <div class="container">
                <nav class="navbar navbar-expand-lg custom_nav-container">
                    <a class="navbar-brand" href="LandingPage.jsp"><img width="300" src="images/logofixfinal.png" alt="#" /></a>
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
                                    <li><a href="userprofile">Account detail: <%= displayUsername %></a></li>
                                    <li><a href="premium.jsp">Premium</a></li>
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
                            <form class="form-inline" action="Search.jsp">
                                <button class="btn my-2 my-sm-0 nav_search-btn" type="submit">
                                    <i class="fa fa-search" aria-hidden="true"></i>
                                </button>
                            </form>
                        </ul>
                    </div>
                </nav>
            </div>
        </header>

        <section class="slider_section">
            <div class="slider_bg_box">
                <video width="rem" autoplay>
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
                                        <h1 style="font-family: 'Radio Canada Big';">
                                            <span>Just copy</span><br>and paste
                                        </h1>
                                        <p>
                                            Are you often worried about making spelling and grammar mistakes in your writing? Let us help you! With our advanced spelling and grammar check website, you no longer have to worry about those minor errors.
                                        </p>
                                        <div class="btn-box">
                                            <a href="signup.jsp" class="btn1">Sign up now!</a>
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
                                        <h1 style="font-family: 'Radio Canada Big';">
                                            <span>Don’t let</span><br>small spelling
                                        </h1>
                                        <p>
                                            and grammar mistakes diminish a good impression. Our spelling and grammar check website uses advanced AI technology to help you detect and correct errors accurately and quickly.
                                        </p>
                                        <div class="btn-box">
                                            <a href="signup.jsp" class="btn1">Sign up now!</a>
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
                                        <h1 style="font-family: 'Radio Canada Big';">
                                            <span>Whether you</span><br>are writing
                                        </h1>
                                        <p>
                                            essays, work emails, or social media posts. Our tool is always ready to assist, ensuring that every word you use is accurate and professional. Try it today and see the difference!
                                        </p>
                                        <div class="btn-box">
                                            <a href="signup.jsp" class="btn1">Sign up now!</a>
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
                <div>
                    <br>
                    <br>
                </div>
            </div>
        </section>

        <section class="why_section layout_padding">
            <div class="container">
                <div class="heading_container heading_center">
                    <h2 style="font-family: Radio Canada Big">
                        Hot sale for our users!
                    </h2>
                </div>
                <br>
                <c:if test="${empty sessionScope.id or sessionScope.premium == 0}">
                <div id="adsCarousel" class="carousel slide" data-ride="carousel" data-interval="3000">
                    <div class="carousel-inner">
                        <c:forEach var="ad" items="${showAds}" varStatus="status">
                            <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                <img src="${ad.imageAd}" class="d-block w-100" style="height: 400px; object-fit: cover;" alt="${ad.title}">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5>${ad.title}</h5>
                                    <p>${ad.description}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <a class="carousel-control-prev" href="#adsCarousel" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#adsCarousel" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </c:if>
            </div>
        </section>

        <jsp:include page="Footer.jsp"></jsp:include>

        <!-- jQuery -->
        <script src="js/jquery-3.4.1.min.js"></script>
        <!-- Popper JS -->
        <script src="js/popper.min.js"></script>
        <!-- Bootstrap JS -->
        <script src="js/bootstrap.js"></script>
        <!-- Custom JS -->
        <script src="js/custom.js"></script>
        <script src="script.js"></script>
    </body>
</html>
