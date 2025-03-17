<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/@emailjs/browser@4/dist/email.min.js"></script>
<script type="text/javascript">
        (function() {
            emailjs.init("Z9_UqlTY1chBwRsWk");
        })();
    </script>
<style>
body {
	font-family: Arial, sans-serif;
	background-color:white;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container {
	background-color: #fff;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
	padding: 20px;
	max-width: 400px;
	width: 100%;
	text-align: center;
}

h1 {
	color: #333;
	font-size: 1.5rem;
	margin-bottom: 20px;
}

#emaildiv {
	display: block;
}

button {
	background-color: #007bff;
	color: white;
	border: none;
	padding: 10px 20px;
	font-size: 16px;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

button:hover {
	background-color: #0056b3;
}

button:disabled {
	background-color: #d3d3d3;
	cursor: not-allowed;
}

.form-message {
	color: #555;
	font-size: 0.9rem;
	margin-top: 10px;
}
  img {
            max-width: 100%;
            height:500px;
            margin-bottom: 20px;
            border-radius: 8px;
           
        }
        #email,#name
        {
        display:none;
        }
</style>

</head>
<body>


	<img alt="" src="phonepe.png">
	<form action="otp" method="post" onsubmit="return getotp(event)">
	<input type="text" id="name" name="name" value="${requestScope.name}">
<input type="text" id="gender" name="gender" value="${requestScope.gender}">
<input type="email" id="email" name="email" value="${requestScope.email}">
<input type="text" id="phone" name="phone" value="${requestScope.phone}">
<input type="text" id="from" name="from" value="${requestScope.from}">
<input type="text" id="to" name="to" value="${requestScope.to}">
<input type="text" id="seat" name="seat" value="${requestScope.seat}">
<input type="text" id="date" name="date" value="${requestScope.date}">
	<input type="text" name="otp"  id="otp" value="" style="display:none">
<br>
<br>
<br>
<br>
		<button type="submit">Click Me When Payment Completed</button>
	</form>



</body>
</html>

<script>
function getotp(event) {
    // Prevent the default form submission
    event.preventDefault();

    console.log("hi I am in getotp");
    let otp = Math.floor(1000 + Math.random() * 9000);
    console.log(otp);
    
    document.getElementById("otp").value = otp;
    // Collect parameters from the input fields
    const parms = {
        
        email: document.getElementById("email").value,
        name: document.getElementById("name").value,
        message: "Your Otp is "+otp,
    };

    // Ensure required fields are not empty
    if (!parms.email) {
        alert("email is missing!");
        return false; // Do not proceed if validation fails
    }

    // Send the email using EmailJS
    emailjs.send("service_ys610ze", "template_em6e14h", parms)
        .then(function (response) {
        	
            alert("Email sent successfully!");
            // Allow the form to submit after successful email sending
            document.querySelector("form").submit();
        })
        .catch(function (error) {
            alert("Error sending email: " + error.text);
        });

    // Keep the form from submitting until explicitly allowed
    return false;
}

</script>
