<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Upgrade to Premium</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        /* Your CSS styles here */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        header {
            background-color: white;
            color: black;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 2px solid #0056b3;
        }
        .logo img {
            height: 40px;
        }
        .text h4 {
            margin: 0;
        }
        .account {
            font-size: 18px;
        }
        .account a {
            color: black;
            text-decoration: none;
            margin-left: 20px;
            transition: color 0.3s;
        }
        .account a:hover {
            color: #ffd700;
        }
        .container {
            padding: 20px;
            text-align: center;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        .item {
            display: flex;
            border: 1px solid #ccc;
            border-radius: 10px;
            margin: 20px auto;
            background-color: white;
            max-width: 800px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .item-left, .item-right {
            width: 50%;
            padding: 20px;
            text-align: center;
            border-right: 1px solid #ccc;
        }
        .item-right {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        .item1 {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .item1 div {
            margin: 10px 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
        }
        button {
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px 15px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #218838;
        }
        .image-container {
            display: none;
            text-align: center;
            margin-top: 20px;
        }
        .image-container img {
            max-width: 100%;
            height: auto;
            margin-bottom: 10px;
        }
        .random-code {
            font-size: 18px;
            font-weight: bold;
        }
        .transfer-content {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <header>
        <div class="logo">
            <a href="LandingPage.jsp"><img  src="images/logofixfinal.png" alt="Logo"></a>
            
        </div>
        <div class="text">
            <h4>Upgrade to Premium</h4>
        </div>
        <div class="account">
             
            <a href="PaymentServlet">Show</a>
</div>

    </header>

    <div class="container">
        <h2>Empower Your Writing with Premium</h2>
        <p>Achieve new heights when you write without limits only 20$</p>
    </div>

    <div class="item">
        <div class="item-left">
            <h1>Free</h1>
            <h3><i  style="color: red"class="fa-solid fa-x"></i>  Check Grammar</h3>
            <h3><i  style="color: green"class="fa-solid fa-check"></i> Check Spelling</h3>
        </div>
        <div class="item-left" style="border: 5px solid green;border-radius: 10px">
            <h1>Premium</h1>
            <h3><i style="color: green"class="fa-solid fa-check"></i>  Check Grammar</h3>
             <h3><i  style="color: green"class="fa-solid fa-check"></i> Check Spelling</h3>
        </div>
        <div class="item-right">
            <div class="item1">
                <div class="transfer-content">
                    <h3></h3>
                    <button onclick="showImage('annual')">Select</button>
                </div>
                
            </div>
        </div>
    </div>

    <div class="image-container" id="imageContainer">
    <form action="payment" method="POST" onsubmit="disableSelectButtons()">
        <img style="height: 272px;width: 300px" id="selectedImage" src="" alt="Selected Plan Image">
        <div class="transfer-content">
            <h3>Transfer Content</h3>
            <p class="random-code" id="randomCode"></p> 
            <input type="hidden" name="randomCode" id="hiddenRandomCode">
        </div>
        <button type="submit">Done</button>
    </form>
        
</div>

<script>
    function showImage(plan) {
    const imageContainer = document.getElementById('imageContainer');
    const selectedImage = document.getElementById('selectedImage');
    const randomDigits = Math.floor(100000 + Math.random() * 900000); // Generate random 6-digit number
    const randomCode = 'chuyentien' + randomDigits; // Random code prefixed with "chuyentien+"
    
    if (plan === 'annual') {
        selectedImage.src = 'images/qrcode.jpg'; // Your annual plan image path
    } else if (plan === 'semi-annual') {
        selectedImage.src = 'images/qrcode.jpg'; // Your semi-annual plan image path
    } else if (plan === 'monthly') {
        selectedImage.src = 'images/qrcode.jpg'; // Your monthly plan image path
    }

    // Show image container
    imageContainer.style.display = 'block';
    // Display random code
    const randomCodeElement = document.getElementById('randomCode');
    randomCodeElement.textContent = randomCode;
    // Set hidden input value
    const hiddenRandomCodeInput = document.getElementById('hiddenRandomCode');
    hiddenRandomCodeInput.value = randomCode;

    // Update random code display in header
    const randomCodeDisplay = document.getElementById('randomCodeDisplay');
    randomCodeDisplay.textContent = randomCode;
}

function disableSelectButtons() {
    // Get all select buttons
    const selectButtons = document.querySelectorAll('button[onclick^="showImage"]');
    
    // Disable all select buttons
    selectButtons.forEach(button => {
        button.disabled = true;
        button.style.backgroundColor = '#6c757d';
    });
}
</script>
</body>
</html>
