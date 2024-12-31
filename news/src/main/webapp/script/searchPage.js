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
        loginButton.style.display = "none";
    } else {
        userInfo.textContent = "请先登录";
        loginButton.textContent = "登录";
    }
}

function onclick_search(){
    var search = document.getElementById("search-content").value;
    if(search === ""){
        alert("搜索内容不能为空");
        return;
    }
    alert("搜索内容为：" + search);
    // location.href = "/search?search=" + search;
}
function onclick_login(){
    location.href = "login";
}