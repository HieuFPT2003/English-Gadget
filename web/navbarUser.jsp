<%-- 
    Document   : navbarUser
    Created on : May 21, 2024, 9:23:53 PM
    Author     : Q.Hieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="images/logoTab-01.png" type="images/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <style>
            .nav{
                display: flex;
                justify-content: space-between;
                padding: 4px;
                background-color: #198754;
            }
            .headerIconBtn{
                font-size: 24px;
                padding:0 2px 0 4px;
                margin-right: 12px;
                border: none;
                background-color: transparent;
            }
            .headerMiddle{
                position: relative;
            }
            .midlleIcon{
                font-size: 20px;
                padding:0 2px 0 4px;
                margin-right: 12px;
                border: none;
                background-color: transparent;
                position: absolute;
                top: 0;
                right: 1%;
            }
            .searchInput{
                width: 320px;
            }
            .headerRight{
                display: flex;
                align-items: center;
                position: relative;
            }
            .rightBtn{
                border: none;
                background-color: transparent;
                text-align: center;
                align-items: center;
                font-size: 20px;
                padding: 6px 8px;
                border-radius: 20px;
                transition: all 0.2s ease-in-out;
            }
            .rightIcon{
                padding-right: 8px;
                font-size: 24px;
            }
            .titleCreate{
                position: absolute;
                top:20%
            }
            .rightBtn:hover{
                background-color: white;
                color: black;
            }
            .listUser{
                margin-right:50px;
                border: none;
                background-color: transparent;
            }
            .navLeft{
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                height: 100%;
                transition: all 0.3s ease-in-out;
            }
            .navLi{
                padding: 12px 8px;
                padding-left: 20px;
            }
            .navLi:hover{
                background-color: #198754;
                color: white;
            }
            .navLink{
                text-decoration: none;
                list-style: none;
                color: black;
            }
            .navLink:hover{
                color: white;
            }
            .leftTop{
                list-style: none;
                padding: 0;
            }
            .leftBotton{
                display: flex;
                justify-content: flex-end;
                padding: 4px 0;
                padding-right:20px ;
            }
            .leftBottonLink{
                text-decoration: none;
                list-style: none;
                color: black;
            }
            /* Custom styles for the modal form */
            .modal-body textarea {
                resize: none;
                border-radius: 5px;
                padding: 10px;
                border: 1px solid #ced4da;
                margin-bottom: 10px;
            }
            .modal-body select {
                width: 100%;
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #ced4da;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <nav class="nav navbar navbar-expand-lg bg-body-tertiarys container-fluid">
            <div class="headerLeft d-flex">
                <div class="leftBar leftBarIcon"> 
                    <button class="headerIconBtn" type="button" data-bs-toggle="offcanvas" data-bs-target="#leftNavbar" aria-controls="staticBackdrop">
                        <i class="bi bi-list"></i>
                    </button>
                </div>
                <div class="logo fs-4">English Gadget</div>
            </div> 
            <!--             search-->
            <div class="headerMiddle d-flex">
                <div class="headerMiddle d-flex">
                    <form action="Search.jsp">
                        <input type="submit" id="fname" name="fname" value="Search Post"><br>
                        </button>
                    </form>
                </div>
            </div>
            <!--            <div class="headerMiddle d-flex">
                            <input class="form-control form-control-sm me-2 searchInput" type="search" placeholder="Search Blog" aria-label="Search" >
                            <i class="bi bi-search midlleIcon"></i>
                        </div>-->
            <div class="headerRight">
                <button type="button" class="rightBtn" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                    <i class="bi bi-plus-lg"></i>
                    Create
                </button>
                <div class="dropdown-center">
                    <button class="dropdown-toggle listUser" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-person-circle rightIcon"></i>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" class="navLink" href="userprofile?userID=${sessionScope.userID}">Profile</a></li>
                        <li><a class="dropdown-item" href="myblog">My Blog</a></li>
                        <li><a class="dropdown-item" href="feedback.jsp">Send Feedback</a></li>
                        <li><a class="dropdown-item" href="LandingPage.jsp">Log Out</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <form class="modal-content" action="blog" method="post">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Create a Post</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <select name="category" id="category">
                            <option value="" disabled selected>Select Category</option>
                            <option value="technology">Technology</option>
                            <option value="education">Education</option>
                            <option value="health">Health</option>
                            <option value="lifestyle">Lifestyle</option>
                        </select>
                        <textarea style="width: 100%; height: 300px;" placeholder="What are you thinking?" name="contentpost" id="contentpost"></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Post</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="offcanvas offcanvas-start" data-bs-backdrop="static" tabindex="-1" id="leftNavbar" aria-labelledby="staticLeftBackdropLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="staticLeftBackdropLabel">English Gadget</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body" style="padding: 4px 0;">
                <div class="navLeft">
                    <ul class="leftTop">
                        <li class="navLi"><a class="navLink fs-5" href="home"> <i class="bi bi-house-door-fill"></i> Home</a></li>
                        <li class="navLi"><a class="navLink fs-5" href="GrammarCheck.jsp"> <i class="bi bi-book-fill"></i> Grammar Checking</a></li>
                        <li class="navLi"><a class="navLink fs-5" href="HelpCenter.jsp"><i class="bi bi-chat-left-text-fill"></i> Help Center</a></li>
                        <li class="navLi"><a class="navLink fs-5" href="myblog"> <i class="bi bi-house-door-fill"></i>My Blog</a></li>
                        <li class="navLi"><a class="navLink fs-5" href="userfeedback"> <i class="bi bi-house-door-fill"></i> Feedback</a></li>

                    </ul>
                    <div class="leftBotton">
                        <a class="leftBottonLink fs-6"><i class="bi bi-info-circle"></i> Help Center</a>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
