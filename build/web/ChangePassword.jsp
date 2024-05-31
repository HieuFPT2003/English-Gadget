<%-- 
    Document   : changePassword
    Created on : May 31, 2024, 12:32:13 AM
    Author     : Admin
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       
    </head>
    <body>
        
        <div class="card" style="padding-top:   300px; margin-top:  100px">
            <h1 style="text-align:center ">
                Grammar Check
            </h1>
            <p>Adding the password will sign you out of all your sessions. You will need to log in again on all your devices.</p>
                    <h5>Change Password</h5>
                    <form action="changePassword" method="post">
                        <div class="form-row ">
                            <div class="form-group col-md-6">
                                <label for="inputEmail4"> Password</label>
                                <input type="password" name="password" value="${password}" class="form-control" id="inputEmail4" placeholder="Password" required>
                            </div>
                           
                            <div class="form-group col-md-6">
                                <label for="inputPassword4">Confirm New Password</label>
                                <input name="confirmpassword" value="${confirmpassword}" type="password" class="form-control" id="inputPassword4" placeholder="Confirm Password" required>
                            </div>
                        </div>
                        <h6 style="color:red">${signupError}</h6>
                        <h6 style="color:green">${signupSuccess}</h6>
                        <button type="submit" class="btn btn-primary" style="width: 100px">Submit</button>
                    </form>
                </div>
    </body>
    <style>

body {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 50vh;
    margin: 0;
    background-color: #f8f9fa; 
}


.card {
    width: 100%;
    max-width: 400px;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 5px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    background-color: #fff;
}


.card h5 {
    text-align: left;
    margin-bottom: 20px;
    font-size: 1.25rem;
    color: #333;
}


.card form {
    padding: 0;
}

.form-row {
    display: flex;
    flex-direction: column;
}

.form-group {
    margin-bottom: 15px;
    width: 100%;
}

.form-group label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
    color: #555;
}

.form-group input {
    width: 100%;
    padding: 10px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
    transition: border-color 0.3s ease;
    font-size: 1rem;
}

.form-group input:focus {
    border-color: #007bff;
}


h6 {
    text-align: center;
    margin-top: 15px;
    font-size: 1rem;
}

h6[style*="color:red"] {
    color: red;
}

h6[style*="color:green"] {
    color: green;
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
}


    </style>
        
</html>

