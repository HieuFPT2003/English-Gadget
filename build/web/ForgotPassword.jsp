<%-- 
    Document   : ForgotPassword
    Created on : May 31, 2024, 12:57:50 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <section class="section__login__form">
            <div class="container">
                <div class="row">
                    <div class=" col-sm-9 col-md-7 col-lg-5 mx-auto">
                        <div class="card border-0 shadow rounded-1" style="margin-top: 150px">
                            <div class="card-body p-3 p-sm-2">
                                <div class="logo mb-3">
                                    <div class="col-md-12 text-center">
                                        <h1>Forgot Password</h1>
                                    </div>
                                </div>
                                <form action="ForgotPassword" method="post">
                                    <div class="form-group">
                                        <input type="email" name="email" id="email" class="form-control" aria-describedby="emailHelp" placeholder="Enter Email" required/>
                                    </div>
                                    <div class="col-md-12 text-center ">
                                        <h6 style="color:red">${emailError}</h6>
                                        <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Change Password</button>
                                    </div>
                                    <div class="col-md-12 ">
                                        <div class="login-or">
                                            <hr class="hr-or">
                                        </div>
                                    </div>
                  
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
    <style>
        body {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 70vh;
    margin: 0;
    background-color: #f8f9fa; /* Light background color */
}
        .section__login__form {
    padding: 50px 0; /* Khoảng cách giữa các phần */
}

.card {
    background-color: #fff; /* Màu nền của thẻ */
}

.card-body {
    padding: 20px; /* Khoảng cách giữa nội dung và viền */
}

.logo h1 {
    color: #333; /* Màu chữ của tiêu đề */
}

.form-group {
    margin-bottom: 20px; /* Khoảng cách giữa các input */
}

.btn-primary {
    display: block;
    margin: 0 auto;
    padding: 10px;
    background-color: #7cbd1e;
    border: none;
    border-radius: 4px;
    color: white;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.btn-primary:hover {
    background-color: #6aad1a;

.tx-tfm {
    text-transform: uppercase; /* Chuyển đổi chữ thành chữ in hoa */
}

.login-or {
    margin-top: 20px; /* Khoảng cách giữa phần login-or và các phần khác */
    text-align: center; /* Căn giữa văn bản */
}
}

    </style>
</html>
