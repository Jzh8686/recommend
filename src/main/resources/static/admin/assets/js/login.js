var baseUrl = "http://localhost:8091";
function login() {
    var username = $("#username").val();
    var password = $("#password").val();
    $.ajax({
        url: baseUrl + "/user/login",
        type: "POST",
        data: {username: username, password: password},
        contentType:"application/x-www-form-urlencoded",
        success: function (result) {
            var resCode = JSON.parse(result).code;
            var mes = JSON.parse(result).message;
            var info = JSON.parse(result).data;
            if (resCode==200){
                window.location.href = 'http://localhost:8091/admin/index.html';
                $.cookie("avatar",info.avatar);
                $.cookie("userId",info.userId);
                $.cookie("username",info.username);
                console.log(result);
            }else {
                alert(mes);
            }
        }
    })
}
