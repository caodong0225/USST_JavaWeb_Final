<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新闻注册页面</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js"></script>
    <script src="script/util.js"></script>
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

        <div class="form-group">
            <label for="birthday">生日</label>
            <input type="date" name="birthday" placeholder="生日" id="birthday" required>
        </div>
        <div class="form-group">
            <label for="sex">性别</label>
            <input type="text" name="sex" placeholder="性别" id="sex" required>
        </div>
        <div class="form-group">
            <label for="career">职业</label>
            <input type="text" name="career" placeholder="职业" id="career" required>
        </div>
        <div class="form-group">
            <label for="country">国籍</label>
            <input type="text" name="country" placeholder="国籍" id="country" required>
        </div>
        <div class="form-group">
            <label for="education">学历:</label>
            <select id="education" name="education" required>
                <option value="high_school">高中</option>
                <option value="associate">专科</option>
                <option value="bachelor">本科</option>
                <option value="master">硕士</option>
                <option value="doctorate">博士</option>
                <!-- 其他学历选项 -->
            </select>
        </div>

        <button type="button" onclick="submitRegisterForm()" class="btn-register">注册</button>
    </form>

    <div id="message" class="message">
        ${message}
    </div>
</div>
</body>
</html>
