function submitRegisterForm() {
    var username= document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var repeatPassword = document.getElementById("repeatPassword").value;
    if (! username || !password || !repeatPassword) {
        document.getElementById("message").innerText = "用户名和密码不能为空。";
        return;
    }
    if (password !== repeatPassword) {
        document.getElementById("message").innerText = "两次密码入的密码不一致。";
        return;
    }
    username = encryptText(username);
    password = encryptText(password);
    var birthday=document.getElementById("birthday").value;
    var sex=document.getElementById("sex").value;
    var career=document.getElementById("career").value;
    var country=document.getElementById("country").value;
    var education=document.getElementById("education").value;
    var xhr = new XMLHttpRequest();

    var apiUrl = "api/register"+"?username="+username+"&password="+password+"&birthday="+birthday+"&sex="+sex+"&career="+career+"&country="+country+"&education="+education;
    xhr.open("POST", apiUrl, true);
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            var response = JSON.parse(xhr.responseText);
            document.getElementById("message").innerText = response.message;
            if (response.success) {
                alert("注册成功");
                window.location.href = "login";
            }
        } else {
            document.getElementById("message").innerText = "注册失败";
        }
    };
    xhr.send();
}