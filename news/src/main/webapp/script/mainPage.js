document.addEventListener('DOMContentLoaded', function() {
    // 检查登录状态并更新用户信息栏
    checkLoginStatus();
    loadTopNews();
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
        loginButton.textContent = "登出";
        loginButton.onclick = onclick_logout;
    } else {
        userInfo.textContent = "请先登录";
        loginButton.textContent = "登录";
        loginButton.onclick = onclick_login;
    }
}

function onclick_search(){
    var search = document.getElementById("search-content").value;
    if(search === ""){
        alert("搜索内容不能为空");
        return;
    }
    // alert("搜索内容为：" + search);
    location.href = "search?search=" + search;
}
function onclick_login(){
    location.href = "login";
}
function onclick_logout(){
    var xhr = new XMLHttpRequest();
    xhr.open('Post', 'api/logout', true);
    xhr.onload = function() {
        location.reload();
    };
    xhr.onerror = function() {
        location.reload();
    };
    xhr.send();
}
function createTopNews(newsData) {
    const topNews = document.getElementById("top-news");
    var thumbnailList = document.getElementById("thumbnail-list");

    var featuredImage = document.getElementById("featured-image");
    var featuredTitle = document.getElementById("featured-image-title");
    // 动态生成缩略图
    var i = 0;
    Object.keys(newsData).forEach(function(newKey) {
        var newsItem = newsData[newKey];
        var cover="api/getImage?imageUrl=" + newsItem["cover"];
        if (i == 0) {
            featuredImage.src = cover;
            featuredTitle.textContent = newsItem["title"];
            i++;
        }
        const thumbnailDiv = document.createElement('div');
        thumbnailDiv.className = 'thumbnail';
        thumbnailDiv.innerHTML = `<img src=${cover} alt="新闻缩略图" />`;
        thumbnailDiv.addEventListener('click', function() {
            featuredImage.src = cover;
            featuredTitle.textContent = newsItem["title"];
        });
        thumbnailList.appendChild(thumbnailDiv);
    });

}
function loadTopNews() {
    var xhr = new XMLHttpRequest();
    xhr.open('Post', 'api/getTopNewsList', false);
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            var response = JSON.parse(xhr.responseText);
            if (response.success) {
                createTopNews(response.data);
            }
        }
    };
    xhr.onerror = function() {
        return null;
    };
    xhr.send();
    // return [
    //     { src: 'img/news/1.png', title: '新闻1标题' },
    //     { src: 'img/news/2.jpg', title: '新闻2标题' },
    //     { src: 'img/news/3.jpg', title: '新闻3标题' },
    //     { src: 'img/news/4.jpg', title: '新闻4标题' },
    //     { src: 'img/news/5.jpg', title: '新闻5标题' },
    // ];
}