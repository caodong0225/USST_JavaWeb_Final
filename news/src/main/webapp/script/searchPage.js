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
    alert("搜索内容为：" + search);
}

function back_main_page() {
    location.href = "mainPage";
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
        newsContent.textContent = `${newsItem["content"].substring(0, 20)}`;
        newsContentDiv.appendChild(newsTitle);
        newsContentDiv.appendChild(newsContent);
        newsDiv.appendChild(newsImageDiv);
        newsDiv.appendChild(newsContentDiv);
        searchedNewsList.appendChild(newsDiv);

        //为新闻容器div添加监视器，用于跳转到详情页面
        newsDiv.addEventListener('click', function () {
            alert("点击")
        });
    });

}

function loadSearchedNews() {
    var xhr = new XMLHttpRequest();
    xhr.open('Post', 'search', false);
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