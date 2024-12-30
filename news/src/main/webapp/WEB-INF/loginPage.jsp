<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新闻登录页面</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js"></script>
    <script src="script/util.js"></script>
    <script src="script/loginPage.js"></script>
    <link rel="stylesheet" href="style/loginPage.css">
</head>
<body>
<div class="login-container">
    <h2>登录</h2>
    <form class="login-form">
        <div class="form-group">
            <label for="username">用户名</label>
            <input type="text" name="username" placeholder="用户名" id="username" required>
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" name="password" placeholder="密码" id="password" required>
        </div>
        <button type="button" onclick="onclick_login()" class="btn-login">登录</button>
    </form>
    <button class="btn-register" onclick="onclick_register()">注册</button>
    <div id="message" class="message">
        ${message}
    </div>
</div>
</body>
</html>
