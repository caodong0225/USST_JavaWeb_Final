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
            <label for="sex">性别:</label>
            <select id="sex" name="sex" required>
                <option value="男">男</option>
                <option value="女">女</option>
                <option value="不愿透露">不愿透露</option>
            </select>
        </div>
        <div class="form-group">
            <label for="career">职业</label>
            <select id="career" name="career" required>
                <option value="学生">学生</option>
                <option value="老师">老师</option>
                <option value="白领">白领</option>
                <option value="蓝领">蓝领</option>
                <option value="研究工作者">研究工作者</option>
                <option value="工程师">工程师</option>
                <option value="服务工作者">服务工作者</option>
                <option value="不愿透露">不愿透露</option>
            </select>
        </div>
        <div class="form-group">
            <label for="country">国籍</label>
            <select id="country" name="country" required>
                <option value="中国">中国</option>
            </select>
        </div>
        <div class="form-group">
            <label for="education">学历:</label>
            <select id="education" name="education" required>
                <option value="初中">初中</option>
                <option value="高中">高中</option>
                <option value="大学">大学</option>
                <option value="不愿透露">不愿透露</option>
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
