<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
        }
        label, input, select {
            display: block;
            width: 100%;
            margin-bottom: 10px;
        }
        label {
            font-weight: bold;
        }
        input[type="text"], input[type="email"], input[type="password"], input[type="tel"], input[type="number"], select {
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #5cb85c;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .password-container {
            position: relative;
            display: flex;
            align-items: center;
        }
        .password-container input {
            flex: 1;
            padding-right: 30px; 
        }
        .toggle-password {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            cursor: pointer;
            font-size: 16px;
            color: #666;
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script>
        function togglePasswordVisibility() {
            var passwordFail = document.getElementById('password');
            var toggleIcon = document.getElementById('toggle-password-icon');
            if (passwordFail.type === 'password') {
                passwordFail.type = 'text';
                toggleIcon.classList.remove('fa-eye');
                toggleIcon.classList.add('fa-eye-slash');
            } else {
                passwordFail.type = 'password';
                toggleIcon.classList.remove('fa-eye-slash');
                toggleIcon.classList.add('fa-eye');
            }
        }
    </script>
</head>
<body>
    <h1>Update User</h1>
    <form action="update" method="post">
        <input type="hidden" name="userID" value="${user.userID}" />
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${user.username}" required />

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${user.email}" required />

        <label for="password">Password:</label>
        <div class="password-container">
            <input type="password" id="password" name="password" placeholder="Enter your password" />
            <i id="toggle-password-icon" class="fas fa-eye toggle-password" onclick="togglePasswordVisibility()"></i>
        </div>

        <label for="phone">Phone:</label>
        <input type="tel" id="phone" name="phone" value="${user.phone}" required />

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${user.address}" required />

        <label for="age">Age:</label>
        <input type="number" id="age" name="age" value="${user.age}" required />

        <label for="premiumID">Premium ID:</label>
        <select id="premiumID" name="premiumID" required>
            <option value="1" ${user.premiumID ? 'selected' : ''}>Yes</option>
            <option value="0" ${!user.premiumID ? 'selected' : ''}>No</option>
        </select>

        <input type="submit" value="Update User" />
    </form>
            <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
