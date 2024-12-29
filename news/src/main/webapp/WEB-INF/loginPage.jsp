<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新闻登录页面</title>
    <style>
        /* 基本的重置样式 */
        body, h2, form, label, input, button, div {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* 页面整体样式 */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        /* 登录容器样式 */
        .login-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }

        /* 标题样式 */
        .login-container h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        /* 表单样式 */
        .login-container form {
            display: flex;
            flex-direction: column;
        }

        /* 标签和输入框样式 */
        .login-container label {
            margin-bottom: 10px;
        }

        .login-container input[type="text"],
        .login-container input[type="password"],
        .login-container input[type="button"],
        #register button {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 3px;
        }

        /* 按钮样式 */
        .login-container input[type="button"],
        #register button {
            background-color: #5cb85c;
            color: white;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .login-container input[type="button"]:hover,
        #register button:hover {
            background-color: #4cae4c;
        }

        /* 注册按钮样式 */
        #register button {
            display: block; /* 使按钮独占一行 */
            width: 100%;
            text-align: center; /* 使文本居中 */
            margin-top: 20px; /* 增加与上方元素的间距 */
        }

        /* 错误消息样式 */
        .message {
            color: red;
            text-align: center;
            margin-top: 10px;
        }
    </style>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js"></script>
    <script src="./script/loginPage.js"></script>
</head>
<body>
<div class="login-container">
    <h2>登录</h2>
    <form action="api/login" method="post">
        <label>
            <input type="text" name="username" placeholder="Username" id="username" required>
        </label>
        <label>
            <input type="password" name="password" placeholder="Password" id="password" required>
        </label>
        <input type="button" value="登录" onclick="submitForm()">
        <input type="submit" value="测试">
    </form>
    <form id="register" action="register" method="get">
        <button>注册</button>
    </form>
    <div id="message" class="message">
        ${message}
    </div>

</div>
</body>
</html>
