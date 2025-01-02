document.addEventListener('DOMContentLoaded', function () {
    // 检查登录状态并更新用户信息栏
    checkLoginStatus();
    loadNewsInfo();
});

function loadNewsInfo() {
    const xhr = new XMLHttpRequest();
    xhr.open('Post', 'detail', false);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            const response = JSON.parse(xhr.responseText);
            if (response.success) {
                const newsInfo = response.data
                document.getElementById("news-title").textContent = newsInfo["title"]
                document.getElementById("news-author").textContent = newsInfo["author"]
                document.getElementById("news-date").textContent = newsInfo["date"]
                document.getElementById("news-cover").src = "api/getImage?imageUrl=" + newsInfo["cover"]
                document.getElementById("news-content-text").innerHTML = `${newsInfo["content"].replace(/\n/g, '<br>')}`
                sendUserAction(newsInfo)
            }
        }
    };
    xhr.onerror = function () {
        return null;
    };
    xhr.send(`newsId=${new URLSearchParams(window.location.search).get('newsId')}`);
}

function sendUserAction(newsInfo) {
    const user = getUser()
    if (user.status) {
        const userInfo = user.info
        userInfo.device = getUserDevice()
        userInfo.preference = translateCNtoEN(newsInfo["zone"]) //这里应该用tag的，不过广告方面只要求一个内容，所以用zone了
        sendAdRequest(userInfo)
    }
}