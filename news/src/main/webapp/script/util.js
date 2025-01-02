function getUserName(successCallback, errorCallback) {
    var xhr = new XMLHttpRequest();
    var url = "api/getUserName";
    xhr.open('POST', url, true);
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            var response = JSON.parse(xhr.responseText);
            if (response.success) {
                successCallback(response);
            } else {
                errorCallback();
            }
        } else {
            errorCallback();
        }
    };
    xhr.onerror = function () {
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

function createImageSrc(imageUrl) {
    return "api/getImage?imageUrl=" + imageUrl;
}

function translateCNtoEN(strCN) {//应广告方面要求，preference接收的参数转换为英文
    switch (strCN) {
        case '时尚':
            return 'Fashion'
        case '艺术':
            return 'Art'
        case '娱乐':
            return 'Entertainment'
        case '教育':
            return 'Education'
        case '宠物':
            return 'Pets'
        case '环保':
            return 'Eco'
        case '气象':
            return 'Weather'
        case '科技':
            return 'Technology'
        case '政治':
            return 'Politics'
        case '经济':
            return 'Economy'
        default:
            return null
    }
}


function getUser() {
    let user = {}
    const xhr = new XMLHttpRequest();
    xhr.open('Post', 'api/getUserInfo', false);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            const response = JSON.parse(xhr.responseText);
            if (response.success) {
                user.info = response.data
                user.status = response["isLogin"]
                if (user.info["age"]) user.info["age"] = Number(user.info.age)
            }
        }
    };
    xhr.onerror = function () {
        user = null;
    };
    xhr.send();
    return user
}

function getUserDevice() {
    device = navigator.platform
    if (device.substring(0, 3) === "Win") {
        return "笔记本电脑"
    } else if (device.substring(0, 5) === "Linux") {
        return "智能手机"
    } else return "其他设备"

}

function sendAdRequest(userInfo) {
    // 定义请求的URL
    const url = 'http://192.168.31.195:8080/user-predict/get-preferences';
    let adData
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json', // 设置请求头，指定发送的数据类型为JSON
        },
        body: JSON.stringify(userInfo), // 将JavaScript对象转换为JSON字符串
    })
        .then(response => response.json()) // 解析JSON格式的响应数据
        .then(data => {
            adData = data;
        })
        .catch((error) => {
            console.error('Error:', error); // 打印错误信息
        });
    return adData;
    //示例
    // return {
    //     "adName": "菜鸟教程",
    //     "adImgUrl": "https://www.runoob.com/wp-content/themes/runoob/assets/img/runoob-logo.png",
    //     "adUrl": "https://www.runoob.com/"
    // }
}

