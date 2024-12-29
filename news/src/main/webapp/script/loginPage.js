function submitForm() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    if (! username || !password) {
        document.getElementById("message").innerText = "用户名和密码不能为空。";
        return;
    }
    if (username.length%2!=0) {
        username += ' ';
    }
    if (password.length%2!=0) {
        password += ' ';
    }
    username = encryptText(username);
    password = encryptText(password);
    var xhr = new XMLHttpRequest();
    var url = "api/login"+"?username="+username+"&password="+password;
    xhr.open('POST', url, true);
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            var response = JSON.parse(xhr.responseText);
            document.getElementById("message").innerText = response.message;
        } else {
            document.getElementById("message").innerText = "登录失败";
        }
    };
    xhr.onerror = function() {
        document.getElementById("message").innerText = "网络错误";
    };
    xhr.send();
}
function encryptText(text) {
    // 将字符串转换为UTF-8编码的二进制数据
    const utf8Encoder = new TextEncoder();
    const encodedText = utf8Encoder.encode(text);
    // 将二进制数据转换为ArrayBuffer
    const buffer = new Uint8Array(encodedText.buffer);
    // 将ArrayBuffer转换为Base64字符串
    const encryptedText = btoa(String.fromCharCode.apply(null, buffer));
    return encryptedText;
}