document.addEventListener('DOMContentLoaded', function() {
    // 检查登录状态并更新用户信息栏
    checkLoginStatus();
    loadTopNews();
    loadNewsZoneSelect();
    loadNewsZoneData("全部");
});

function onclick_search(){
    var search = document.getElementById("search-content").value;
    if(search === ""){
        alert("搜索内容不能为空");
        return;
    }
    // alert("搜索内容为：" + search);
    location.href = "search?search=" + search;
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
}
function createTopNews(newsData) {
    var featuredDiv = document.getElementById("featured-div");
    var thumbnailList = document.getElementById("thumbnail-list");
    var featuredImage = document.getElementById("featured-image");
    var featuredTitle = document.getElementById("featured-image-title");
    // 动态生成缩略图
    var i = 0;
    Object.keys(newsData).forEach(function(newKey) {
        var newsItem = newsData[newKey];
        var cover=createImageSrc(newsItem["cover"]);
        if (i == 0) {
            featuredImage.src = cover;
            featuredTitle.textContent = newsItem["title"];
            featuredDiv.addEventListener('click',function(){
                location.href = "detail?newsId=" + newsItem["id"];
            })
            i++;
        }
        const thumbnailDiv = document.createElement('div');
        thumbnailDiv.className = 'thumbnail';
        thumbnailDiv.innerHTML = `<img src=${cover} alt="新闻缩略图" />`;
        thumbnailDiv.addEventListener('click', function() {
            featuredImage.src = cover;
            featuredTitle.textContent = newsItem["title"];
            featuredDiv.addEventListener('click',function(){
                location.href = "detail?newsId=" + newsItem["id"];
            })
        });
        thumbnailList.appendChild(thumbnailDiv);
    });
}
function loadNewsZoneSelect(){
    var xhr = new XMLHttpRequest();
    xhr.open('Post', 'api/getNewsZoneList', false);
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            var response = JSON.parse(xhr.responseText);
            if (response.success) {
                createNewsZoneSelect(response.data);
            }
        }
    };
    xhr.onerror = function() {
        return null;
    };
    xhr.send();
}
function createNewsZoneSelect(zoneData) {
    var newsZoneList = document.getElementById("select-zone-list");
    for(var i = 0; i < zoneData.length; i++){
        var zone = zoneData[i];
        var zoneDiv = document.createElement('div');
        zoneDiv.className = 'zone';
        var button = document.createElement('button');
        button.textContent = zone;
        (function(zone) {
            button.addEventListener('click', function() {
                loadNewsZoneData(zone);
            });
        })(zone);
        zoneDiv.appendChild(button);
        newsZoneList.appendChild(zoneDiv);
    }
}
function loadNewsZoneData(zone){
    var xhr = new XMLHttpRequest();
    xhr.open('Post', 'api/getNewsList?zone=' + zone, false);
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            var response = JSON.parse(xhr.responseText);
            if (response.success) {
                createNewsList(response.data);
            }
        }
    };
    xhr.onerror = function() {
        return null;
    };
    xhr.send();
}
function createNewsList(newsData) {
    var newsList = document.getElementById("more-news-list");
    newsList.innerHTML = '';
    Object.keys(newsData).forEach(function(newsKey) {
        var newsItem = newsData[newsKey];
        var newsDiv = document.createElement('div');
        newsDiv.className = 'more-news';
        newsDiv.innerHTML = `
            <div class="more-news-image">
                <img src=${createImageSrc(newsItem["cover"])} alt="新闻缩略图">
            </div>
            <div class="more-news-content">
                <h3>${newsItem["title"]}</h3>
                <p>${cutText(newsItem["content"],200)}</p>
            </div>
        `;
        newsDiv.addEventListener('click', function(){
            location.href = "detail?newsId=" + newsItem["id"];
        });
        newsList.appendChild(newsDiv);
    });
}
function createImageSrc(imageUrl){
    return "api/getImage?imageUrl=" + imageUrl;
}
