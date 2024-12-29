<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新闻页面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .header, .search-bar, .top-news, .more-stories {
            padding: 20px;
        }
        .user-info, .category-buttons {
            text-align: right;
        }
        .user-info button, .category-buttons button {
            padding: 10px;
            margin: 5px;
        }
        .search-bar input {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 3px;
        }
        .top-news{
            background-color: #f9f9f9;
            margin-top: 20px;
            padding: 20px;
            display: flex;
            max-height: 80%;
        }
        .thumbnail-list {
            display: block;
            justify-content: space-between;
            margin-bottom: 20px;
            width: 25%;
            max-height: 90%;
            overflow-y: auto;
            text-align: center;
        }
        .thumbnail {
            width: 90%;
            height: 30%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            overflow: hidden;
            margin: 10px;
        }
        .thumbnail img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        .thumbnail h3 {
            padding: 10px;
            background-color: #f4f4f4;
            margin: 0;
        }
        .featured-image {
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            overflow: hidden;
            text-align: center;
        }
        .featured-image img {
            width: 90%;
            object-fit: cover;
        }
        .featured-image h2 {
            padding: 20px;
            background-color: #f4f4f4;
            margin: 0;
        }
        .select-class-list {
            height: 50px;
            width: 90%;
            display: flex;
            justify-content: left;
            margin-top: 20px;
        }
        .select {
            margin: 10px;
        }
        .select button {
            padding: 10px;
            margin: 5px;
            border: 1px solid #ddd;
            border-radius: 3px;
            height: 100%;
            width: 100px;
            text-align: center;
            line-height: 100%;
        }
        .more-news-list {
            display: block;
            justify-content: space-between;
            margin-top: 20px;
        }
        .more-news {
            width: 85%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            overflow: hidden;
            margin: 10px;
            display: flex;
        }
        .more-news-image {
            width: 20%;
            height: 10%;
            overflow: hidden;
        }
        .more-news-image img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        .more-news-content {
            padding: 10px;
        }


    </style>
</head>
<body>
<div class="header">
    <div class="user-info">
        <span>用户信息栏</span>
        <button>登录</button>
        <button>注册</button>
    </div>
</div>

<div class="search-bar">
    <input type="text" placeholder="搜索新闻...">
</div>

<div class="top-news">
    <div class="thumbnail-list">
        <div class="thumbnail">
            <img src="img/undefined.png" alt="新闻缩略图">
        </div>
        <div class="thumbnail">
            <img src="img/undefined.png" alt="新闻缩略图">
        </div>
        <div class="thumbnail">
            <img src="img/undefined.png" alt="新闻缩略图">
        </div>
        <div class="thumbnail">
            <img src="img/undefined.png" alt="新闻缩略图">
        </div>
        <div class="thumbnail">
            <img src="img/undefined.png" alt="新闻缩略图">
        </div>
    </div>
    <div class="featured-image">
        <img src="img/undefined.png" alt="新闻大图">
        <h2>新闻1标题</h2>
    </div>
</div>
<div class="select-class-list">
    <div class="select">
        <button>全部</button>
    </div>
    <div class="select">
        <button>国内</button>
    </div>
    <div class="select">
        <button>国内</button>
    </div>
    <div class="select">
        <button>国内</button>
    </div>
    <div class="select">
        <button>国内</button>
    </div>
    <div class="select">
        <button>国内</button>
    </div>
    <div class="select">
        <button>国内</button>
    </div>
    <div class="select">
        <button>国内</button>
    </div>
    <div class="select">
        <button>国内</button>
    </div>
    <div class="select">
        <button>国内</button>
    </div>
    <div class="select">
        <button>国内</button>
    </div>
    <div class="select">
        <button>国内</button>
    </div>
</div>
<div class="more-news-list">
    <div class="more-news">
        <div class="more-news-image">
            <img src="img/undefined.png" alt="新闻缩略图">
        </div>
        <div class="more-news-content">
            <h3>新闻1标题</h3>
            <p>新闻1内容</p>
        </div>
    </div>
    <div class="more-news">
        <div class="more-news-image">
            <img src="img/undefined.png" alt="新闻缩略图">
        </div>
        <div class="more-news-content">
            <h3>新闻2标题</h3>
            <p>新闻2内容</p>
        </div>
    </div>
    <div class="more-news">
        <div class="more-news-image">
            <img src="img/undefined.png" alt="新闻缩略图">
        </div>
        <div class="more-news-content">
            <h3>新闻3标题</h3>
            <p>新闻3内容</p>
        </div>
    </div>
    <div class="more-news">
        <div class="more-news-image">
            <img src="img/undefined.png" alt="新闻缩略图">
        </div>
        <div class="more-news-content">
            <h3>新闻4标题</h3>
            <p>新闻4内容</p>
        </div>
    </div>
    <div class="more-news">
        <div class="more-news-image">
            <img src="img/undefined.png" alt="新闻缩略图">
        </div>
        <div class="more-news-content">
            <h3>新闻5标题</h3>
            <p>新闻5内容</p>
        </div>
    </div>
</div>
</body>
</html>