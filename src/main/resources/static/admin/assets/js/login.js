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
            var info = JSON.parse(result).data;
            $.cookie("avatar",info.avatar);
            $.cookie("userId",info.userId);
            if (resCode==200){
                window.location.href = 'http://localhost:8091/admin/index.html';
                console.log(result);
            }else {
                alert(result.message);
            }
        }
    })
}
