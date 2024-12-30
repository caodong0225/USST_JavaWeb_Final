function onclick_search(){
    var search = document.getElementById("search-content").value;
    if(search == ""){
        alert("搜索内容不能为空");
        return;
    }
    alert("搜索内容为：" + search);
    // location.href = "/search?search=" + search;
}
function onclick_login(){
    location.href = "login";
}