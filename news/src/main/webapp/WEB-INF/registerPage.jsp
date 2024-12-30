<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新闻注册页面</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js"></script>
    <script src="script/registerPage.js"></script>
    <link rel="stylesheet" href="style/registerPage.css">
</head>
<body>
<div class="register-container">
    <h2>注册</h2>
    <form id="register-form">
        <div class="form-group">
            <label for="username">用户名</label>
            <input type="text" name="username" placeholder="用户名" id="username" required>
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" name="password" placeholder="密码" id="password" required>
        </div>
        <div class="form-group">
            <label for="repeatPassword">再次输入密码</label>
            <input type="password" name="repeatPassword" placeholder="再次输入密码" id="repeatPassword" required>
        </div>
        <button type="button" onclick="submitRegisterForm()" class="btn-register">注册</button>
    </form>

    <div id="message" class="message">
        ${message}
    </div>
</div>
</body>
</html>
