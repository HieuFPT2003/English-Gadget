<%-- 
    Document   : login
    Created on : May 8, 2024, 3:05:11 PM
    Author     : khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login Page</title>      
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>

        <h2>Sign in</h2>
        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <form action="#">
                    <h2>Create Account</h2>
                    <div class="social-container">
                        <a href="url"></a>
                        <a href="#" class="social"><i class="fa-brands fa-facebook"></i></a>
                        <a href="#" class="social"><i class="fa-brands fa-facebook"></i>></a>
                    </div>
                    <span>or use your email for registration</span>
                    <input type="text" placeholder="Name" />
                    <input type="email" placeholder="Email" />
                    <input type="password" placeholder="Password" />
                    <button>Sign Up</button>
                </form>
            </div>

            <div class="form-container sign-in-container">
                <form action="login" method="post">
                    <h1>Sign in</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:9999/English_Gadget/loginwithgoogle&response_type=code&client_id=1058288513168-95klp5p5jtdvdq7ftj0nn0oj29ac7a9i.apps.googleusercontent.com&approval_prompt=force" class="social"><i class="fab fa-google-plus-g"></i></a>
                        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>or use your account</span>
                    <div class="wrapper1 content">
                        <%
          HttpSession currentSession = request.getSession();
          String username = (String) currentSession.getAttribute("username");
           String password = (String) currentSession.getAttribute("password");
                        %>
                        <input style="width: 209px"name="username" type="username" placeholder="username" value="<%= username != null ? username : "" %>"/>
                    </div>

                    <div class="wrapper2" style="position: relative">
                        <input id="password" name="password" type="password" placeholder="password" value="<%= password != null ? password : "" %>"/>
                        <span class="show-hide">
                            <i style="position: relative;left: 80px;bottom: 38px"class="btn fa-solid fa-eye"></i>
                        </span>
                    </div>



                    <a href="forgetpass.jsp">Forgot your password?</a>
                    <button type="submit">Sign In</button>
                    <h6 style="color:red">${mess}</h6>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>let go sign in</h1>
                        <p>To keep connected with us please login with your personal username and password</p>
                        <button class="ghost" id="signIn">Sign In</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Welcome to English Checker</h1>
                        <p>Let sign up your personal details and start with us</p>
                        <a href="signup.jsp"><button class="ghost" id="signup">Sign Up</button></a>
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
