<%-- 
    Document   : Checking
    Created on : May 24, 2024, 10:34:23 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
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
        <title>MAGIC GADGET - Home</title>
        <!-- bootstrap core css -->
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
        <!-- font awesome style -->
        <link href="css/font-awesome.min.css" rel="stylesheet" />
        <!-- Custom styles for this template -->
        <link href="css/style.css" rel="stylesheet" />
        <!-- responsive style -->
        <link href="css/responsive.css" rel="stylesheet" />
    </head>
    <!-- Header -->
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
                                <li><a href="Checking.jsp">Grammar Check</a></li>
                                <li><a href="Blog.jsp">Blog</a></li>
                            </ul>

                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"> <span class="nav-label">Pages <span class="caret"></span></a>

                            <ul class="dropdown-menu">
                                <li><a href="about.html">About</a></li>
                                <li><a href="Checc.html">Testimonial</a></li>
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
                                <li><a href="about.html">Development team</a></li>
                                <li><a href="contact.html">Contact</a></li>
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
    <body>
        <div class="container">
            <div class="header">Choose language: 
                <select id="language"> 
                    <option value="en">English (US)</option>
                    <option value="fr">French</option>
                    <option value="es">Spanish</option>
                    <option value="de">German</option>
                    <option value="all">All</option>
                </select>
            </div>
            <div class="editor">
                <div class="toolbar">
                    <button onclick="undo()">&#8630;</button>
                    <button onclick="redo()">&#8631;</button>
                    <select id="fontSize" onchange="changeFontSize(this.value)">
                        <option value="16px">16px</option>
                        <option value="18px">18px</option>
                        <option value="20px">20px</option>
                    </select>
                    <select id="fontFamily" onchange="changeFontFamily(this.value)">
                        <option value="Open Sans">Open sans</option>
                    </select>
                    <button onclick="boldText()">B</button>
                    <button onclick="italicText()">I</button>
                    <button onclick="underlineText()">U</button>
                    <button onclick="uploadDocument()">Upload Document</button>
                </div>
                <textarea  placeholder="Start by writing, pasting (Ctrl + V) text, or uploading a document (doc, pdf)."></textarea>
            </div>
            <div class="buttons">
                <button onclick="pasteText()">Submit</button>


            </div>
        </div>
        <script src="scripts.js"></script>
    </body>
    <jsp:include page="Footer.jsp"></jsp:include>
</html>
