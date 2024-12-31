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
    // 将ArrayBuffer转换为Base64字符串
    const encryptedText = btoa(String.fromCharCode.apply(null, buffer));
    return encryptedText;
}
function cutText(text, maxLength) {
    if (text.length > maxLength) {
        text = text.substring(0, maxLength) + '.....';
    }
    return text;
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