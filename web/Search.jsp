<%-- 
    Document   : Searcb
    Created on : Jun 5, 2024, 8:07:34 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <jsp:include page="Header.jsp"></jsp:include>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <link href="https://fonts.googleapis.com/css?family=Poppins:400,800" rel="stylesheet" />
            <link href="css/main.css" rel="stylesheet" />
            <link rel="icon" href="images/logoTab-01.png" type="images/x-icon">
            <title>MAGIC GADGET</title>
        </head>
        <body>
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
            <div class="s004" style="background-image: url('images/img.jpg')">
                <form action="searchUser" method="post">
                    <fieldset style = "justify-content: center">
                        <legend>WHAT POST ARE YOU LOOKING FOR?</legend>
                        <div class="inner-form">
                            <div class="input-field">
                                <input class="form-control" id="choices-text-preset-values" name = "txt" type="text" placeholder="Type to search..." />
                                <button class="btn-search" type="submit">
                                    <svg  width="24" height="24" viewBox="0 0 24 24">
                                    <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"></path>
                                    </svg>
                                </button>
                            </div>
                        </div>
                        <!--                    <div class="suggestion-wrap">
                                                <span></span>
                                                <span>SWP391</span>
                                                <span>SE1839</span>
                                                <span>FPTU</span>
                                                <span>FAP</span>
                                            </div>-->
                    </fieldset>
                </form>
            </div>
            <script src="js/extention/choices.js"></script>
            <script>
                var textPresetVal = new Choices('#choices-text-preset-values',
                        {
                            removeItemButton: true,
                        });

            </script>
        </body>
    <jsp:include page="Footer.jsp"></jsp:include>
</html>


