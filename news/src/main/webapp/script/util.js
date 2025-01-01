function getUserName(successCallback, errorCallback) {
    var xhr = new XMLHttpRequest();
    var url = "api/getUserName";
    xhr.open('POST', url, true);
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            var response = JSON.parse(xhr.responseText);
            if (response.success) {
                successCallback(response);
            }
            else {
                errorCallback();
            }
        } else {
            errorCallback();
        }
    };
    xhr.onerror = function() {
        errorCallback();
    };
    xhr.send();
}

function encryptText(text) {
    if (typeof text !== "string") {
        return;
    }
    // 将字符串转换为UTF-8编码的二进制数据
    const utf8Encoder = new TextEncoder();
    const encodedText = utf8Encoder.encode(text);
    // 将二进制数据转换为ArrayBuffer
    const buffer = new Uint8Array(encodedText.buffer);
    // 将ArrayBuffer转换为Base64字符串并返回
    return btoa(String.fromCharCode.apply(null, buffer));
}
function cutText(text, maxLength) {
    if (text.length > maxLength) {
        text = text.substring(0, maxLength) + '.....';
    }
    return text;
}

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