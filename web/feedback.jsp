<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
     <link rel="icon" href="images/logoTab-01.png" type="images/x-icon">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>English Gadget</title>
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
            background-color: #4CAF50; /* Bootstrap primary color */
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
            <form action="sendfeedback" method="post">
                
                <div class="form-group">
                    <label for="feedbackTopic">Feedback Topic:</label>
                    <select id="feedbackTopic" name="feedbackTopic" class="form-control" required>
                        <option value="">Select a topic</option>
                        <option value="Spelling">Spelling </option>
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
                <button type="submit" class="btn btn-custom">Save</button>
            </form>
        </div>
    </div>
</body>
</html>