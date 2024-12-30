document.addEventListener('DOMContentLoaded', function() {
    // 检查登录状态并更新用户信息栏
    checkLoginStatus();

    createTopNews();
});

function checkLoginStatus() {
    const userInfo = document.getElementById('user-info');
    const loginButton = document.getElementById('login-button');

    getUserName(function(json) {
        // alert("json"+JSON.stringify(json));
        updateUserInfo(json.username, userInfo, loginButton);
    }, function() {
        updateUserInfo(null, userInfo, loginButton);
    });
}
function updateUserInfo(username, userInfo, loginButton) {
    if (username) {
        userInfo.textContent = "欢迎，" + username;
        loginButton.style.display = "none";
    } else {
        userInfo.textContent = "请先登录";
        loginButton.textContent = "登录";
    }
}

function onclick_search(){
    var search = document.getElementById("search-content").value;
    if(search == ""){
        alert("搜索内容不能为空");
        return;
    }
    alert("搜索内容为：" + search);
    // location.href = "/search?search=" + search;
}
function onclick_login(){
    location.href = "login";
}
function createTopNews() {
    var topNews = document.getElementById("top-news");
    var thumbnailList = document.getElementById("thumbnail-list");

    var featuredImage = document.getElementById("featured-image");
    var featuredTitle = document.getElementById("featured-image-title");

    var newsData = getTopNewsData();

    if (!newsData || newsData.length === 0) {
        return;
    }

    // 动态生成缩略图
    newsData.forEach(function(newsItem) {
        const thumbnailDiv = document.createElement('div');
        thumbnailDiv.className = 'thumbnail';
        thumbnailDiv.innerHTML = `<img src="${newsItem.src}" alt="新闻缩略图" />`;
        thumbnailDiv.addEventListener('click', function() {
            featuredImage.src = newsItem.src;
            featuredTitle.textContent = newsItem.title;
        });
        thumbnailList.appendChild(thumbnailDiv);
    });
    featuredImage.src = newsData[0].src;
    featuredTitle.textContent = newsData[0].title;
}
function getTopNewsData() {
    return [
        { src: 'img/news/1.png', title: '新闻1标题' },
        { src: 'img/news/2.jpg', title: '新闻2标题' },
        { src: 'img/news/3.jpg', title: '新闻3标题' },
        { src: 'img/news/4.jpg', title: '新闻4标题' },
        { src: 'img/news/5.jpg', title: '新闻5标题' },
    ];
}