<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/detailPage.css">
    <script src="script/util.js"></script>
    <script src="script/detailPage.js"></script>
    <title>新闻详情</title>
</head>
<body>
<div class="header">
    <div class="user-info">
        <span id="user-info">用户信息栏</span>
        <button id="login-button" onclick="onclick_login()">登录</button>
    </div>
</div>

<div class="news-body">
    <div class="news-header">
        <h1 id="news-title">标题</h1>
        <div>
            <span id="news-author">发布组织</span>
            <span id="news-date">2025/01/01</span>
        </div>
    </div>
</div>

<div class="news-body">
    <div class="news-content">
        <img id="news-cover" alt="新闻封面" src="">
        <p id="news-content-text">正文</p>
    </div>
</div>

</body>
</html>