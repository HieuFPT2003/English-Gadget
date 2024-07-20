<%-- 
    Document   : changepass
    Created on : Jul 12, 2024, 4:59:47 PM
    Author     : khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
    </script>
    <style>
        .mainDiv {
    display: flex;
    min-height: 100%;
    align-items: center;
    justify-content: center;
    background-color: #f9f9f9;
    font-family: 'Open Sans', sans-serif;
  }
 .cardStyle {
    width: 500px;
    border-color: white;
    background: #fff;
    padding: 36px 0;
    border-radius: 4px;
    margin: 30px 0;
    box-shadow: 0px 0 2px 0 rgba(0,0,0,0.25);
  }
#signupLogo {
  max-height: 100px;
  margin: auto;
  display: flex;
  flex-direction: column;
}
.formTitle{
  font-weight: 600;
  margin-top: 20px;
  color: #2F2D3B;
  text-align: center;
}
.inputLabel {
  font-size: 12px;
  color: #555;
  margin-bottom: 6px;
  margin-top: 24px;
}
  .inputDiv {
    width: 70%;
    display: flex;
    flex-direction: column;
    margin: auto;
  }
input {
  height: 40px;
  font-size: 16px;
  border-radius: 4px;
  border: none;
  border: solid 1px #ccc;
  padding: 0 11px;
}
input:disabled {
  cursor: not-allowed;
  border: solid 1px #eee;
}
.buttonWrapper {
  margin-top: 40px;
}
  .submitButton {
    width: 70%;
    height: 40px;
    margin: auto;
    display: block;
    color: #fff;
    background-color: #065492;
    border-color: #065492;
    text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.12);
    box-shadow: 0 2px 0 rgba(0, 0, 0, 0.035);
    border-radius: 4px;
    font-size: 14px;
    cursor: pointer;
  }
.submitButton:disabled,
button[disabled] {
  border: 1px solid #cccccc;
  background-color: #cccccc;
  color: #666666;
}
.mess{
    
      background-color: #499557;
    border-radius: 20px;
    display: none;
    padding: 10px;
    margin: 10px;
    display: block;
    width: 347px;
    text-align: center;
    margin-left: 75px;
}
.mess h6{
    color:white;
}


    </style>
</head>
    <body>
      <header class="header_section">
        <div class="container">
            <nav class="navbar navbar-expand-lg custom_nav-container">
                <a class="navbar-brand" href="LandingPage.jsp">
                    <img width="300" src="images/logofixfinal.png" alt="#" /></a>
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
                                <li><a href="userprofile">Account detail: <%= displayUsername %></a></li>
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

   
</header>
<div class="mainDiv">
    <div class="cardStyle"> 
        <form action="changePassword" method="post">
            <img src="" id="signupLogo"/>
            <h2 class="formTitle">Change Password</h2> 
            <div class="inputDiv"> 
                <label class="inputLabel" for="current-password">Current Password</label>
                <input type="password" id="current-password" name="current-password" required> 
            </div> 
            <div class="inputDiv"> 
                <label class="inputLabel" for="new-password">New Password</label>
                <input type="password" id="new-password" name="new-password" required> 
            </div> 
            <div class="inputDiv">
                <label class="inputLabel" for="confirmPassword">Confirm Password</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required> 
            </div>
            <div class="buttonWrapper"> 
                <button type="submit" id="submitButton" class="submitButton pure-button pure-button-primary"> 
                    <span>Change</span> 
                    <span id="loader"></span> 
                </button> 
            </div> 
            <div class="mess">
            <h6 style="text-align: center">${messfail}</h6>
            <h6 style="text-align: center">${messSuc}</h6>
            </div>
               
            
            
        </form> 
    </div> 
</div>
            <script>
                document.addEventListener('DOMContentLoaded', function() {
    var messfail = '<%= request.getAttribute("messfail") != null ? request.getAttribute("messfail") : "" %>';
    var messSuc = '<%= request.getAttribute("messSuc") != null ? request.getAttribute("messSuc") : "" %>';

    var messDiv = document.querySelector('.mess');

    if (messfail || messSuc) {
        messDiv.style.display = 'block';
        messDiv.innerHTML = `
            <h6 style="text-align: center">${messfail}</h6>
            <h6 style="text-align: center">${messSuc}</h6>
        `;
    } else {
        messDiv.style.display = 'none';
    }
});
            </script>
        <jsp:include page="Footer.jsp" />
          <!-- jQuery -->
    <script src="js/jquery-3.4.1.min.js"></script>
    <!-- Popper JS -->
    <script src="js/popper.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="js/bootstrap.js"></script>
    <!-- Custom JS -->
    <script src="js/custom.js"></script>
    </body>
</html>
