<%-- 
    Document   : signup
    Created on : May 18, 2024, 7:59:13 PM
    Author     : khanh
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SignUp Page</title>
        <link href="css/signup.css" rel="stylesheet" type="text/css"/>
              <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    </head>
    <body>
        <h2>Sign in/up</h2>
        <div class="container" id="container" style="min-height: 626px  ">
            <div class="form-container sign-in-container">
                <form action="signup" method="post" style="padding: 18px 50px">
                    <h2>Create Account</h2>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fa-brands fa-facebook"></i></i></a>
                        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>or use your email for registration</span>
                    
                     <%
            HttpSession currentSession = request.getSession();
            String username = (String) currentSession.getAttribute("username");
             String email = (String) currentSession.getAttribute("email");
             String password = (String) currentSession.getAttribute("password");
              String phone = (String) currentSession.getAttribute("password");
               String address = (String) currentSession.getAttribute("address");
                String age = (String) currentSession.getAttribute("age");
        %>
        <input name="email" type="email" placeholder="Email" value="<%= email != null ? email : "" %>"/>
                    
        <input name="username" type="text" placeholder="username" value="<%= username != null ? username : "" %>" />
                     <div class="wrapper2" style="position: relative">
                         <input style=" width: 281px"id="password" name="password" type="password" placeholder="Password" value="<%= password != null ? password : "" %>"/>
                        <span class="show-hide">
                            <i style="position: relative;left: 134px;bottom: 38px"class="btn fa-solid fa-eye"></i>
                        </span>
                    </div>
                  <input name="phone" type="text" placeholder="phone" value="<%= phone != null ? phone : "" %>" />
                  <input name="address" type="text" placeholder="Address" value="<%= address != null ? address : "" %>" />
                  <input name="age" type="text" placeholder="age" value="<%= age != null ? age : "" %>" />
                    <button type="submit">Sign Up</button>
                    <h6 style="color: red">${mess}</h6>
                </form>
            </div>

            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-right">
                        <h1>Let go Sign in</h1>
                        <p>To keep connected with us please login with your personal username and password</p>
                        <a href="login.jsp"><button class="ghost" id="signIn">Sign In</button></a>
                    </div>

                </div>
            </div>
        </div>

        <footer>

        </footer>
                 <script type="text/javascript">
            const input = document.getElementById("password");
            const toggle = document.querySelector(".btn");
            toggle.addEventListener("click", () => {
                if (input.type === "password") {
                    input.type = "text";
                    toggle.classList.replace("fa-eye", "fa-eye-slash");
                } else {
                    input.type = "password";
                    toggle.classList.replace("fa-eye-slash", "fa-eye");
                }
            });
        </script>

    </body>
</html>

    </body>
</html>
