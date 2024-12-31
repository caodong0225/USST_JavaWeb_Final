<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/searchPage.css">
    <script src="script/util.js"></script>
    <script src="script/searchPage.js"></script>
    <title>搜索新闻</title>
</head>
<body>
<div class="header">
    <div class="user-info">
        <span id="user-info">用户信息栏</span>
        <button id="login-button" onclick="onclick_login()">登录</button>
    </div>
</div>

<div class="search-bar">
    <label for="search-content"></label>
    <input id="search-content" type="text" placeholder="搜索新闻...">
    <button class="search-button" onclick="onclick_search()">搜索</button>
    <button class="back-to-main-page-button" onclick="back_main_page()">返回主页</button>
</div>
<div class="searched-news-list" id="searched-news-list">

</div>
</body>
</html>