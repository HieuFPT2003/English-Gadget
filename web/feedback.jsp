<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Boolean role = (Boolean) session.getAttribute("role");
    if (role != null || role) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="Header.jsp" />
    </head>
    <script>
        var userID = <%= session.getAttribute("userID") != null ? "\"" + session.getAttribute("userID") + "\"" : "null" %>;
        var premium = <%= session.getAttribute("premium") != null ? "\"" + session.getAttribute("premium") + "\"" : "null" %>;
        var role = <%= session.getAttribute("role") != null ? "\"" + session.getAttribute("role") + "\"" : "null" %>;
        var name = <%= session.getAttribute("usernamegoogle") != null ? "\"" + session.getAttribute("usernamegoogle") + "\"" : "null" %>;

        console.log("User ID: " + userID);
        console.log("Premium: " + premium);
        console.log("Role: " + role);
        console.log("Name: " + name);
    </script>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Form</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body {
            background-color: #ffffff;
            color: #000000;
            font-family: Arial, sans-serif;
        }
        .form-container {
            background-color: #f2f2f2; 
            padding: 30px;
            border-radius: 10px;
            margin-top: 50px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1); 
        }
        .form-container h1 {
            color: #333333; 
            margin-bottom: 20px; 
        }
        .form-control:focus {
            border-color: #80bdff;
            box-shadow: 0 0 0 0.2rem rgba(0,123,255,.25); 
        }
        .btn-custom {
            background-color: #4CAF50; 
            color: #ffffff;
            border: none;
        }
        .btn-custom:hover {
            background-color: #0056b3; 
            color: #ffffff;
        }
        .star-rating {
            direction: rtl;
            display: inline-block;
            padding: 20px;
        }
        .star-rating input[type=radio] {
            display: none;
        }
        .star-rating label {
            color: #ccc;
            font-size: 30px;
            padding: 0;
            cursor: pointer;
        }
        .star-rating input[type=radio]:checked ~ label {
            color: #f90;
        }
        .star-rating label:hover,
        .star-rating label:hover ~ label,
        .star-rating input[type=radio]:checked ~ label,
        .star-rating input[type=radio]:checked ~ label ~ label {
            color: #f90;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h1>Feedback Form</h1>
            <form action="sendfeedback" method="post">
                <div class="form-group">
                    <label for="feedbackTopic">Feedback Topic:</label>
                    <select id="feedbackTopic" name="feedbackTopic" class="form-control" required>
                        <option value="">Select a topic</option>
                        <option value="Spelling">Spelling</option>
                        <option value="Sentence Structure">Sentence Structure</option>
                        <option value="Punctuation">Punctuation</option>
                        <option value="Subject-Verb Agreement">Subject-Verb Agreement</option>
                        <option value="Verb Tenses">Verb Tenses</option>
                        <option value="Word Usage">Word Usage</option>
                        <option value="Clarity and Conciseness">Clarity and Conciseness</option>
                        <option value="Misused Words">Misused Words</option>
                        <option value="Plagiarism Detection">Plagiarism Detection</option>
                        <option value="Comma Usage">Comma Usage</option>
                        <option value="Paragraph Structure">Paragraph Structure</option>
                        <option value="Preposition Usage">Preposition Usage</option>
                        <option value="Conjunction Use">Conjunction Use</option>
                        <option value="Repetition of Words">Repetition of Words</option>
                        <option value="Vocabulary Enhancement">Vocabulary Enhancement</option>
                        <option value="Word Limit">Word Limit</option>
                        <option value="Advanced Grammar">Advanced Grammar</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="feedbackText">Feedback Detail:</label>
                    <textarea id="feedbackText" name="feedbackText" rows="4" class="form-control" required></textarea>
                </div>
                <div class="form-group">
                    <label for="starRating">Rate Us:</label>
                    <div class="star-rating">
                        <input type="radio" id="5-stars" name="rating" value="5">
                        <label for="5-stars" class="star">&#9733;</label>
                        <input type="radio" id="4-stars" name="rating" value="4">
                        <label for="4-stars" class="star">&#9733;</label>
                        <input type="radio" id="3-stars" name="rating" value="3">
                        <label for="3-stars" class="star">&#9733;</label>
                        <input type="radio" id="2-stars" name="rating" value="2">
                        <label for="2-stars" class="star">&#9733;</label>
                        <input type="radio" id="1-star" name="rating" value="1">
                        <label for="1-star" class="star">&#9733;</label>
                    </div>
                </div>
                <button type="submit" class="btn btn-custom">Save</button>
            </form>
        </div>
    </div>
</body>
</html>
