<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     <link rel="icon" href="images/logoTab-01.png" type="images/x-icon">
 <title>English Gadget</title>
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
        input[type="text"], input[type="email"], input[type="tel"], input[type="number"], select {
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[readonly] {
            background-color: #f0f0f0; /* Gray background for read-only */
            pointer-events: none; /* Disable editing */
        }
        input[type="submit"] {
            background-color: #5cb85c;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>Update User</h1>
    <form action="userupdate" method="post">
        <input type="hidden" name="userID" value="${user.userID}" />
        
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${user.username}"  />

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${user.email}"  />

        <label for="phone">Phone:</label>
        <input type="tel" id="phone" name="phone" value="${user.phone}"  />

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${user.address}"  />

        <label for="age">Age:</label>
        <input type="number" id="age" name="age" value="${user.age}"  />

        <input type="submit" value="Update User" />
    </form>
    <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
