<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Form</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #ffffff;
            color: #000000;
            font-family: Arial, sans-serif; /* Added a generic font stack for better compatibility */
        }
        .form-container {
            background-color: #f2f2f2; /* Changed background color for better readability */
            padding: 30px;
            border-radius: 10px;
            margin-top: 50px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1); /* Added a subtle box shadow for depth */
        }
        .form-container h1 {
            color: #333333; /* Darkened the text color for better contrast */
            margin-bottom: 20px; /* Added margin bottom for spacing */
        }
        .form-control:focus {
            border-color: #80bdff; /* Changed focus color of inputs for better UX */
            box-shadow: 0 0 0 0.2rem rgba(0,123,255,.25); /* Added focus style for inputs */
        }
        .btn-custom {
            background-color: #007bff; /* Bootstrap primary color */
            color: #ffffff;
            border: none;
        }
        .btn-custom:hover {
            background-color: #0056b3; /* Darker shade for hover */
            color: #ffffff;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h1>Feedback Form</h1>
            <form action="feedbackadd" method="post">
                <div class="form-group">
                    <label for="feedbackTopic">Feedback Topic:</label>
                    <input type="text" id="feedbackTopic" name="feedbackTopic" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="feedbackText">Feedback Text:</label>
                    <textarea id="feedbackText" name="feedbackText" rows="4" class="form-control" required></textarea>
                </div>
                <button type="submit" class="btn btn-custom">Save</button>
            </form>
        </div>
    </div>
</body>
</html>
