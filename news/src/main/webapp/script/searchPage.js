document.addEventListener('DOMContentLoaded', function () {
    // 检查登录状态并更新用户信息栏
    checkLoginStatus();
    //自动填充搜索栏
    fillSearchBar();
    //加载搜索结果
    loadSearchedNews()

});

function checkLoginStatus() {
    const userInfo = document.getElementById('user-info');
    const loginButton = document.getElementById('login-button');

    getUserName(function (json) {
        // alert("json"+JSON.stringify(json));
        updateUserInfo(json.username, userInfo, loginButton);
    }, function () {
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

function onclick_search() {
    var search = document.getElementById("search-content").value;
    if (search === "") {
        alert("搜索内容不能为空");
        return;
    }
    alert("搜索内容为：" + search);
}

function onclick_login() {
    location.href = "login";
}

function onclick_logout() {
    var xhr = new XMLHttpRequest();
    xhr.open('Post', 'api/logout', true);
    xhr.onload = function () {
        location.reload();
    };
    xhr.onerror = function () {
        location.reload();
    };
    xhr.send();
}

function fillSearchBar() {
    const searchParams = new URLSearchParams(window.location.search);
    const searchBar = document.getElementById("search-content")
    searchBar.value = searchParams.get('search')
}

function createSearchedNews(newsData) {
    // 获取目标容器div
    var searchedNewsList = document.getElementById('searched-news-list');

    Object.keys(newsData).forEach(function (newKey) {
        var newsItem = newsData[newKey];
        var newsDiv = document.createElement('div');
        newsDiv.className = 'news';
        var newsImageDiv = document.createElement('div');
        newsImageDiv.className = 'news-image';
        var img = document.createElement('img');
        img.src = `${"api/getImage?imageUrl=" + newsItem["cover"]}`;
        img.alt = '新闻缩略图';
        newsImageDiv.appendChild(img);
        var newsContentDiv = document.createElement('div');
        newsContentDiv.className = 'news-content';
        var newsTitle = document.createElement('h3');
        newsTitle.textContent = `${newsItem["title"]}`;
        var newsContent = document.createElement('p');
        newsContent.textContent = `${newsItem["content"].substring(0,20)}`;
        newsContentDiv.appendChild(newsTitle);
        newsContentDiv.appendChild(newsContent);
        newsDiv.appendChild(newsImageDiv);
        newsDiv.appendChild(newsContentDiv);
        //为新闻容器div添加监视器，用于跳转到详情页面
        newsDiv.addEventListener('click', function () {
            alert("点击")
        });
        // 将新闻容器div添加到目标容器div中
        searchedNewsList.appendChild(newsDiv);
    });

}

function loadSearchedNews() {
    var xhr = new XMLHttpRequest();
    xhr.open('Post', 'api/getTopNewsList', false);
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            var response = JSON.parse(xhr.responseText);
            if (response.success) {
                createSearchedNews(response.data);
            }
        }
    };
    xhr.onerror = function () {
        return null;
    };
    xhr.send();
}