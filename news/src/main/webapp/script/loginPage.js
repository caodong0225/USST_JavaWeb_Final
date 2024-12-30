function onclick_login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    if (! username || !password) {
        document.getElementById("message").innerText = "用户名和密码不能为空。";
        return;
    }
    username = encryptText(username);
    password = encryptText(password);
    var xhr = new XMLHttpRequest();
    var url = "api/login"+"?username="+username+"&password="+password;
    xhr.open('POST', url, true);
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            var response = JSON.parse(xhr.responseText);
            if (response.success) {
                window.location.href = "mainPage";
                return;
            }
            else {
                document.getElementById("message").innerText = response.message;
            }
        } else {
            document.getElementById("message").innerText = "登录失败";
        }
    };
    xhr.onerror = function() {
        document.getElementById("message").innerText = "网络错误";
    };
    xhr.send();
}
function onclick_register(){
    location.href = "register";
}