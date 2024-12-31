<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/mainPage.css">
    <script src="script/util.js"></script>
    <script src="script/mainPage.js"></script>
    <title>新闻页面</title>
</head>
<body>
<div class="header">
    <div class="user-info">
        <span id="user-info">用户信息栏</span>
        <button id="login-button" onclick="onclick_login()">登录</button>
    </div>
</div>

<div class="search-bar">
    <input id="search-content" type="text" placeholder="搜索新闻...">
    <button onclick="onclick_search()">搜索</button>
</div>

<div class="top-news">
    <div id="thumbnail-list" class="thumbnail-list">
<%--        <div class="thumbnail">--%>
<%--            <img src="img/undefined.png" alt="新闻缩略图">--%>
<%--        </div>--%>
    </div>
    <div class="featured">
        <div class="featured-image">
            <img id="featured-image" src="" alt="新闻大图">
        </div>
        <div class="featured-content">
            <h2 id="featured-image-title">新闻1标题</h2>
        </div>
    </div>
</div>
<div class="select-zone-list" id="select-zone-list">
<%--    <div class="zone">--%>
<%--        <button>全部</button>--%>
<%--    </div>--%>
</div>
<div class="more-news-and-advertisement">

    <div class="more-news-list" id="more-news-list">
    </div>
    <div class="advertisement-list">
        <div class="advertisement">
            <div class="advertisement-image">
                <img src="img/undefined.png" alt="广告图片">
            </div>
            <h4>广告1标题</h4>
        </div>
        <div class="advertisement">
            <div class="advertisement-image">
                <img src="img/undefined.png" alt="广告图片">
            </div>
            <h4>广告1标题</h4>
        </div>
    </div>
</div>
</body>
</html>