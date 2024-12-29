<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新闻注册页面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f0f0;
        }
        .register-container {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .register-container h2 {
            margin-bottom: 20px;
        }
        .register-container input[type="text"],
        .register-container input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        .register-container input[type="button"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        .register-container input[type="button"]:hover {
            background-color: #45a049;
        }
        .message {
            margin-top: 10px;
        }.message:empty {
             display: none;
         }
    </style>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js">
    </script>
    <script src="script/registerPage.js"></script>
</head>
<body>

<div class="register-container">
    <h2>注册</h2>
    <form>
        <label >
            <input type="text" name="username" placeholder="用户名" id="username" required>
        </label>
        <label>
            <input type="password" name="password" placeholder="密码" id="password" required>
        </label>
        <label>
            <input type="password" name="repeatPassword" placeholder="再次输入密码" id="repeatPassword" required>
        </label>
        <input type="button" value="注册" onclick="submitRegisterForm()">
    </form>

    <div id="message" class="message">
        ${message}
    </div>
</div>
</body>
</html>
