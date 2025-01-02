document.addEventListener('DOMContentLoaded', function () {
    // 检查登录状态并更新用户信息栏
    checkLoginStatus();
    //自动填充搜索栏
    fillSearchBar();
    //加载搜索结果
    loadSearchedNews()

});

function onclick_search() {
    var search = document.getElementById("search-content").value;
    if (search === "") {
        alert("搜索内容不能为空");
        return;
    }
    location.href = "search?search=" + search;
}

function back_main_page() {
    location.href = "mainPage";
}

function getSearchText() {
    const searchParams = new URLSearchParams(window.location.search);
    return searchParams.get('search')
}

function fillSearchBar() {
    document.getElementById("search-content").value = getSearchText()
}

function createSearchedNews(newsData) {
    // 获取目标容器div
    var searchedNewsList = document.getElementById('searched-news-list');

    Object.keys(newsData).forEach(function (newKey) {
        var newsItem = newsData[newKey];
        var newsDiv = document.createElement('div');
        newsDiv.className = 'news';
        newsDiv.innerHTML += `
            <div class="news-image">
                <img src=${createImageSrc(newsItem["cover"])} alt="新闻缩略图">
            </div>
            <div class="news-content">
                <h3>${newsItem["title"]}</h3>
                <p>${cutText(newsItem["content"], 100)}</p>
            </div>
        `;
        searchedNewsList.appendChild(newsDiv);

        //为新闻容器div添加监听器，用于跳转到详情页面
        newsDiv.addEventListener('click', function(){
            location.href = "detail?newsId=" + newsItem["id"];
        });
    });
}

function loadSearchedNews() {
    const xhr = new XMLHttpRequest();
    xhr.open('Post', 'search', false);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            const response = JSON.parse(xhr.responseText);
            if (response.success) {
                createSearchedNews(response.data);
            }
        }
    };
    xhr.onerror = function () {
        return null;
    };
    xhr.send(`search=${getSearchText()}`);
}