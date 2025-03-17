<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OTP Verification</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
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

    input[type="text"] {
        width: 90%;
        padding: 10px;
        font-size: 14px;
        border: 1px solid #ccc;
        border-radius: 4px;
        margin-bottom: 20px;
        box-sizing: border-box;
    }

    input[type="text"]:focus {
        outline: none;
        border-color: #007bff;
        box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
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

    button:active {
        transform: scale(0.98);
    }
</style>
</head>
<body>
    <div class="container">
        <h1>Re-enter OTP Verification</h1>
        <form action="otpthere">
         
          <input type="number" name="otpback" value="${otpback}" style="display:block">
            <input type="text" name="otp" id="otp" placeholder="Enter received OTP from email">
            <br>
            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>
