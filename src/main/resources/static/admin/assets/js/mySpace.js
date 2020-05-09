var baseUrl="http://localhost:8091";
var user;
var newAvatar;
function indexSexFunction() {
    var dom = document.getElementById("right_chart");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    var data = genData(10);

    option = {
        title : {
            text: '会员性别统计',

            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 10,
            top: 20,
            bottom: 20,
            data: data.legendData,

            selected: data.selected

        },
        series : [
            {
                name: '性别',
                type: 'pie',
                radius : '55%',
                center: ['40%', '50%'],
                data: data.seriesData,

                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    function genData(count) {
        var dataMap;
        $.ajax({url:baseUrl+"/statistics/sex",async:false,success :function getAllCity(result) {
                dataMap=result.data;

            } });
        var i=0;

        var namelist=new Array(dataMap.length);
        var legendData=[];
        var seriesData=[];
        var selected = {};
        for(var m=0;m<dataMap.length;m++){
            namelist[m]=dataMap[m];
            var name=namelist[i].location;

            legendData.push(name);
            seriesData.push({
                name: name,
                value: namelist[i].value
            });
            selected[name] = i < 6;
            i++;
        }
        /*    for(var key in dataMap111){
                namelist[i]=key;
                var name=namelist[i];

                legendData.push(name);
                seriesData.push({
                    name: name,
                    value: dataMap111.key
                });
                selected[name] = i < 6;
                i++;
            }*/

        return {
            legendData: legendData,
            seriesData: seriesData,
            selected: selected
        };

        /*


          var nameList = ["江苏","上海","安徽","湖南","湖北","福建","北京","浙江","甘肃","江西"]

       var legendData = [];
        var seriesData = [];
        var selected = {};
       for (var i = 0; i < 10; i++) {
            debugger;
           name =nameList[i];
            legendData.push(name);
            seriesData.push({
                name: name,
                value: Math.round(Math.random() * 100000)
            });
            selected[name] = i < 6;
        }*/


        function makeWord(max, min) {
            var nameLen = Math.ceil(Math.random() * max + min);
            var name = [];
            for (var i = 0; i < nameLen; i++) {
                name.push(nameList[Math.round(Math.random() * nameList.length - 1)]);
            }
            return name.join('');
        }
    }
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}
function indexCityFunction() {
    var dom = document.getElementById("left_chart");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    var data = genData(10);

    option = {
        title : {
            text: '会员地区统计',

            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 10,
            top: 20,
            bottom: 20,
            data: data.legendData,

            selected: data.selected

        },
        series : [
            {
                name: '地区',
                type: 'pie',
                radius : '55%',
                center: ['40%', '50%'],
                data: data.seriesData,

                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    function genData(count) {
        var dataMap;
        $.ajax({url:baseUrl+"/statistics/location",async:false,success :function getAllCity(result) {
            dataMap=result.data;

            } });
        var i=0;

        var namelist=new Array(dataMap.length);
        var legendData=[];
        var seriesData=[];
        var selected = {};
        for(var m=0;m<dataMap.length;m++){
            namelist[m]=dataMap[m];
            var name=namelist[i].location;

            legendData.push(name);
            seriesData.push({
                name: name,
                value: namelist[i].value
            });
            selected[name] = i < 6;
            i++;
        }
    /*    for(var key in dataMap111){
            namelist[i]=key;
            var name=namelist[i];

            legendData.push(name);
            seriesData.push({
                name: name,
                value: dataMap111.key
            });
            selected[name] = i < 6;
            i++;
        }*/

        return {
            legendData: legendData,
            seriesData: seriesData,
            selected: selected
        };

        /*


          var nameList = ["江苏","上海","安徽","湖南","湖北","福建","北京","浙江","甘肃","江西"]

       var legendData = [];
        var seriesData = [];
        var selected = {};
       for (var i = 0; i < 10; i++) {
            debugger;
           name =nameList[i];
            legendData.push(name);
            seriesData.push({
                name: name,
                value: Math.round(Math.random() * 100000)
            });
            selected[name] = i < 6;
        }*/


        function makeWord(max, min) {
            var nameLen = Math.ceil(Math.random() * max + min);
            var name = [];
            for (var i = 0; i < nameLen; i++) {
                name.push(nameList[Math.round(Math.random() * nameList.length - 1)]);
            }
            return name.join('');
        }
    }
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}
function mySpaceInfo() {
    var id=$.cookie("userId");
    if(id==null||id=="")
        return;

    $.ajax({url:baseUrl+"/user/info?userId="+id,async:true,success: function mySpaceSuccess(result){
        user=result.data;
        var id=$.cookie('id');
        user.id=id;
        $("#userName").html(user.username);
        $("#realName").val(user.realname);
        $("#age").val(user.age);
        $("#sex").val(user.sex);
        $("#location").val(user.location);
        $("#tel").val(user.tel);
        $("#email").val(user.email);
        $("#prompt").html(user.prompt);
     }
    })
}
function  updatePassword() {
    var newPassword=$("#newPassword").val();
    user.password =newPassword;
    var temp=user;
    delete temp._links;
    var i=JSON.stringify(temp);
    $.ajax({url:baseUrl+"/user/changePassword",type:"POST",data:i,success:function (result) {

        alert("修改成功");
        },contentType:'application/json;charset=utf-8'})
}
function toRegister() {
    $("#confirmPassword").toggle();
    $("#loginLoginButton").toggle();
    $("#loginRegisterButton").toggle();
    $("#registerRegisterButton").toggle();
    $("#registerLoginButton").toggle();
    $("#registerForm").toggle();
    $("#loginForm").toggle();
}
/*function loginOnclick() {
    debugger;
    var username=$("#login_username").val();
    var password=$("#login_password1").val();
    debugger;
    $.ajax({url:"http://localhost:8080/login",async:true,type:"POST",data:{username:username,password:password},success:function () {
        debugger;
        $("#openLoginModal").toggle();
        $("#logout").toggle();
        }})
}*/
function registerUser() {
    var username=$("#register_username").val();
    var password1=$("#register_password1").val();
    var password2=$("#register_password2").val();
    if(username==""){
        $("#tips").html("请输入账号");
        $("#tips").show();
        return ;
    }
    if(password1==""||password2==""){
        $("#tips").html("请输入密码");
        $("#tips").show();
        return ;
    }
    var ret = /^(.){3,20}$/;
    if(!ret.test(password1)||!ret.test(password2)){
        $("#tips").html("密码格式不符合要求");
        $("#tips").show();
        return ;
    }
    if(password1==password2){
        $("#tips").hide();
        $.ajax({url:baseUrl+"/register",async:true,type:'POST',data:{"username":username,"password":password1},success:function isSuccess(result) {

                if(result.msg=="success"){

                    alert("注册成功,请登陆");
                  /*  $.ajax({url:"http:localhost:8080/pages/index.html",async:true});*/
                 window.location.href=baseUrl+"/pages/index.html";

                }
                if(result.msg=="fail"){
                    alert("注册失败")
                }
            }});
    }
    else {
        $("#tips").show();
        return ;
    }
}
/*function toLogin() {
    $("#confirmPassword").toggle();
    $("#loginLoginButton").toggle();
    $("#loginRegisterButton").toggle();
    $("#registerRegisterButtonButton").toggle();
    $("#registerLoginButton").toggle();

}*/
/*
function mySpaceSuccess( result) {
    debugger;

}*/
function upload(){
   var formData = new FormData();


    formData.append("file",$("#imgUrl")[0].files[0]);
    formData.append("userId",$.cookie("userId"));
    $.ajax({url:baseUrl+"/user/upload",type:"POST",data:formData,success:function (result) {
       alert("上传成功");
       newAvatar=result.data;
       console.log(newAvatar);
       /*$("#avatar").attr("src","/"+result.filename);*/
        }   ,processData : false, contentType : false});


}
function preview111() {
   /* var baseUrl="E:\\图片\\";
    debugger;
    var filePath=$("#imgUrl").val();
        filePath=filePath.substring(filePath.lastIndexOf("\\")+1);
    var imgUrl=baseUrl+filePath;
    debugger;
    $("#imgPreview").attr("src",imgUrl);

    $("#imgPreview").show();*/
}
function getImgURL(obj) {
    $(":file").css("color","white");
    var url = null;
    var fileObj =obj.files[0];

    if (window.createObjcectURL != undefined) {
        url = window.createOjcectURL(fileObj);
    } else if (window.URL != undefined) {
        url = window.URL.createObjectURL(fileObj);
    } else if (window.webkitURL != undefined) {
        url = window.webkitURL.createObjectURL(fileObj);
    }
    $("#imgPreview").attr("src",url);

    $("#imgPreview").show();
}
function confirmUpdateAvatar() {
   user.avatar=newAvatar;
   var temp=user;
   delete temp._links;
   var i=JSON.stringify(temp);
    $.ajax({url:baseUrl+"/user/updateInfo",type:"POST",data:i,success:function (result) {
        debugger;
            alert("保存成功");
            $.cookie("avatar",newAvatar);
            $("#avatar").attr("src","assets/img/avatar/"+$.cookie("avatar"));
            $("#avatar2").attr("src","assets/img/avatar/"+$.cookie("avatar"));
        },contentType:'application/json;charset=utf-8'})

}
function updateInfo() {
    user.realname=$("#realName").val();
    user.tel=$("#tel").val();
    user.sex=$("#sex").val();
    /* user.email=$("#email").val();*/
     user.location=$("#location").val();
     user.age=$("#age").val();
    user.prompt=$("#prompt").html();
    
  /*  var res=validateInputData(user);
    if(res.msg=="success"){

    }
    else{
        alert(res.msg);
        return;
    }*/
    var   regular=/^(?:[1-9][0-9]?|1[01][0-9]|120)$/;
    debugger;
    if(regular.test(user.age)||user.age==""){

    }
    else {
        alert("请输入正确的年龄");
        return ;
    }
    var reg =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    var telReg=/^1[2,3,4,5,6,7,8,9][0-9]{9}$/;
    if(telReg.test(user.tel)||user.tel==""){

    }
    else {
        alert("请输入正确的手机号");
        return ;
    }
    var i=JSON.stringify(user);
    $.ajax({url:baseUrl+"/user/updateInfo",type:"POST",data:i,success:function (result) {
            debugger;
            alert("修改成功");
            result=result.data;
            debugger;
            $("#realName").val(result.realname);
            $("#sex").val(result.sex);
            $("#age").val(result.age);
            $("#sex").val(result.sex);
            $("#location").val(result.location);
            $("#tel").val(result.tel);
            $("#email").val(result.email);
            $("#prompt").html(result.prompt);
        },contentType:'application/json;charset=utf-8'})

}
function loadHead() {

    $(".include").each(function() {
        if (!!$(this).attr("file")) {

            var $includeObj = $(this);
            $(this).load($(this).attr("file")
            /*    , function(html) {
             /!*   $includeObj.html(html);*!/
                /!*$includeObj.after(html).remove(); *!///加载的文件内容写入到当前标签后面并移除当前标签
            }*/)
        }
    });

}
function logout() {
    debugger;
    $.ajax({url:baseUrl+"/logout",type:"POST",async:true,success:function (result) {
        debugger;
            $.cookie("id",null,{expires:-1,path:"/"});
            debugger;
            window.location.href=baseUrl+"/pages/index.html";
            /*$("#openLoginModal").toggle();
            $("#logout").toggle();*/
        }})
}
function isLogin() {
    var id=$.cookie("id");

    if(typeof (id)=="string"&&id!=null&&id!=""){
        $("#openLoginModal").hide();
        $("#logout").show();
    }
    else {
        $("#openLoginModal").show();
        $("#logout").hide();
        }
}
function updatePrompt() {
    user.prompt=$("#promptContent").val();
    var i=JSON.stringify(user);
    debugger;
    $.ajax({url:baseUrl+"/user/updateInfo",type:"POST",data:i,success:function (result) {
            debugger;
            alert("修改成功");
            $("#prompt").html(result.data.prompt);
        },contentType:'application/json;charset=utf-8'})
}
function retrievePassword() {
    debugger;
    $("#closeLoginModal").click();
    debugger;
    setTimeout(function(){
        $("#openRetrievePasswordModal").click();
    }, 1000);

   /* var email=user.email;
    var prefix=email.substring(0,4);
    var suffer=email.substring(email.lastIndexOf("@")-3);
    var showEmail=prefix+"****"+suffer;
    debugger;
    $("#emailInput").html(showEmail);*/

}
function confirmRetrieve() {
    var username=$("#usernameInput").val();
    var checkCode=$("#checkCode").val();
    var url=baseUrl+"/resetPassword";
    $.ajax({url:url,type:"POST",data:{userId:$.cookie("userId"),checkCode:checkCode},async:true,success:function (result) {
        if(result.msg=="success"){
            $("#closeRetrievePassword").click();
            setTimeout(function () {
                alert("密码已重置为"+result.newPassword+"请尽快修改密码");
            },500);

        }
        else {
            alert("验证码错误")
        }
        }})
}
function getCheckCode() {
    var username=$("#usernameInput").val();
    var email=$("#emailInput").val();
    var url=baseUrl+"/getCheckCode?userId="+$.cookie("userId")+"&email="+email;
    $.ajax({url:url,type:"GET",async:true,success:function (result) {

        }})
}

function search() {

    debugger;
   $("body > div#tableDiv > table > tbody").empty();
   var username=$("#inputUsername").val();
   var searchResult;
   var url=baseUrl+"/user/search/findByUsernameLike?size=5&page=0&username=%25"+username+"%25";
    $.ajax({url:url,type:"GET",async:true,success:function (result) {
        searchResult=result._embedded.user;
            if(searchResult.length==0){
                alert("没有符合结果");
                return;
            }
            for(var i=0;i<searchResult.length;i++){
                var temp=searchResult[i];
                var idTemp=temp._links.self.href;
                var id=idTemp.substring(idTemp.lastIndexOf("/")+1);
                var hr="../pages/item.html?id="+id;
                var m="<tr><td><a href="+hr+" onclick='toUpdatePage()'>" + temp.username + "</a></td><td>"+temp.realname+"</td><td>" + temp.sex + "</td><td>" + temp.age + "</td><td>" + temp.location + "</td><td>" + temp.email + "</td><td>" + temp.tel + "</td><td><button class='btn-primary'style=' border-radius: 8px;'onclick='toAClick("+id+")'>修改</button><button class='btn-danger'style=' border-radius: 8px;margin-left: 4px' onclick='deleteUser("+id+")'>删除</button></td></tr>";
                $("body > div#tableDiv > table > tbody").append(m);
            }
            debugger;
            //$.noConflict();
            loadFoot(result.page.totalPages);

        }})

}
function  loadFoot(totalPages) {
    var zp = {
        init:function(obj,pageinit){
            return (function(){
                zp.addhtml(obj,pageinit);
                zp.bindEvent(obj,pageinit);
            }());
        },
        addhtml:function(obj,pageinit){
            return (function(){
                obj.empty();
                /*上一页*/
                if (pageinit.current > 1) {
                    obj.append('<a href="javascript:;" class="prebtn">上一页</a>');
                } else{
                    obj.remove('.prevPage');
                    obj.append('<span class="disabled">上一页</span>');
                }
                /*中间页*/
           /*     if (pageinit.current >4 && pageinit.pageNum > 4) {
                    obj.append('<a href="javascript:;" class="zxfPagenum">'+1+'</a>');
                    obj.append('<a href="javascript:;" class="zxfPagenum">'+2+'</a>');
                    obj.append('<span>...</span>');
                }*/
                debugger;
                //当前页数大于4，总页数总于9 current=(4,9]
                if (pageinit.current >4 && pageinit.current <= pageinit.pageNum-5) {
                    //start=(2,7],end=(6,11]
                    var start  = pageinit.current - 2,end = pageinit.current + 2;
                }
                //当前页数大于4，总页数小于9
                else if(pageinit.current >4 && pageinit.current > pageinit.pageNum-5){
                    var start  = pageinit.pageNum - 4,end = pageinit.pageNum;
                }else{
                    var start = 1,end = 9;
                }
                for (;start <= end;start++) {
                    if (start <= pageinit.pageNum && start >=1) {
                        if (start == pageinit.current) {
                            obj.append('<span class="current">'+ start +'</span>');
                        } else if(start == pageinit.current+1){
                            obj.append('<a href="javascript:;" class="zxfPagenum nextpage">'+ start +'</a>');
                        }else{
                            obj.append('<a href="javascript:;" class="zxfPagenum">'+ start +'</a>');
                        }
                    }
                }
                if (end < pageinit.pageNum) {
                    obj.append('<span>...</span>');
                }
               /*if(pageinit.pageNum <=5){
                   for(var x=1;x<=pageinit.pageNum;x++){
                       debugger;
                       if (x == pageinit.current) {
                           obj.append('<span class="current">'+ x +'</span>');
                       } else if(x == pageinit.current+1){
                           obj.append('<a href="javascript:;" class="zxfPagenum nextpage">'+ x +'</a>');
                       }else{
                           obj.append('<a href="javascript:;" class="zxfPagenum">'+ x+'</a>');
                       }
                   }
               }
               else {
                 var start,end;
                 if(pageinit.pageNum-pageinit.current<5){
                     start=pageinit.pageNum-5;
                     end=pageinit.current;
                 }
               }*/
                /*下一页*/
                if (pageinit.current >= pageinit.pageNum) {
                    obj.remove('.nextbtn');
                    obj.append('<span class="disabled">下一页</span>');
                } else{
                    obj.append('<a href="javascript:;" class="nextbtn">下一页</a>');
                }
                /*尾部*/
                obj.append('<span>'+'共'+'<b>'+pageinit.pageNum+'</b>'+'页，'+'</span>');
                obj.append('<span>'+'到第'+'<input type="number" class="zxfinput" />'+'页'+'</span>');
                obj.append('<span class="zxfokbtn">'+'确定'+'</span>');
            }());
        },
        bindEvent:function(obj,pageinit){
            return (function(){
                obj.on("click","a.prebtn",function(){
                    var cur = parseInt(obj.children("span.current").text());
                    var current = $.extend(pageinit, {"current":cur-1});
                    zp.addhtml(obj,current);
                    if (typeof(pageinit.backfun)=="function") {
                        pageinit.backfun(current);
                    }
                });
                obj.on("click","a.zxfPagenum",function(){
                    var cur = parseInt($(this).text());
                    var current = $.extend(pageinit, {"current":cur});
                    zp.addhtml(obj,current);
                    if (typeof(pageinit.backfun)=="function") {
                        pageinit.backfun(current);
                    }
                });
                obj.on("click","a.nextbtn",function(){
                    var cur = parseInt(obj.children("span.current").text());
                    var current = $.extend(pageinit, {"current":cur+1});
                    zp.addhtml(obj,current);
                    if (typeof(pageinit.backfun)=="function") {
                        pageinit.backfun(current);
                    }
                });
                obj.on("click","span.zxfokbtn",function(){
                    var cur = parseInt($("input.zxfinput").val());
                    var current = $.extend(pageinit, {"current":cur});
                    zp.addhtml(obj,{"current":cur,"pageNum":pageinit.pageNum});
                    if (typeof(pageinit.backfun)=="function") {
                        pageinit.backfun(current);
                    }
                });
            }());
        }
    };
    $.fn.createPage = function(options){
        var pageinit = $.extend({
            pageNum : 15,
            current : 1,
            backfun : function(){}
        },options);
        zp.init(this,pageinit);
    };
    $(".zxf_pagediv").createPage({
        pageNum: totalPages,
        current: 1,
        backfun: function (e) {
            debugger;
            var page = e.current - 1;
            $("body > div#tableDiv > table > tbody").empty();
            var username = $("#usernameInput").val();
            var searchResult;
            var url = baseUrl+"/user/search/findByUsernameLike?size=5&username=%25" + username + "%25&page=" + page;
            $.ajax({
                url: url, type: "GET", async: false, success: function (result) {
                    searchResult = result._embedded.user;
                    if (searchResult.length == 0) {
                        alert("没有符合结果");
                        return;
                    }
                    e.pageNum=result.page.totalPages;
                    for (var i = 0; i < 5; i++) {
                        var temp = searchResult[i];
                        var idTemp=temp._links.self.href;
                        var id=idTemp.substring(idTemp.lastIndexOf("/")+1);
                        var hr="../pages/item.html?id="+id;
                        var m="<tr><td><a href="+hr+" onclick='toUpdatePage()'>" + temp.username + "</a></td><td>"+temp.realname+"</td><td>" + temp.sex + "</td><td>" + temp.age + "</td><td>" + temp.location + "</td><td>" + temp.email + "</td><td>" + temp.tel + "</td><td><button class='btn-primary'style=' border-radius: 8px;'onclick='toAClick("+id+")'>修改</button><button class='btn-danger'style=' border-radius: 8px;margin-left: 4px' onclick='deleteUser("+id+")'>删除</button></td></tr>";
                        $("body > div#tableDiv > table > tbody").append(m);
                    }
                }
            })
        }
    });
}
function toUpdatePage() {

    var i=$("a").attr("herf");
    debugger;
    var id=i.substring(i.lastIndexOf("="));
    window.location.href=i;
}
function loadUserInfo() {
   var id=GetQueryString("id");
   debugger;
    $.ajax({url:baseUrl+"/user/"+id,async:true,success: function mySpaceSuccess(result){
            $("#userName1").html(result.username);
            $("#realName1").val(result.realname);
            $("#age1").val(result.age);
            $("#location1").val(result.location);
            $("#tel1").val(result.tel);
            $("#email1").val(result.email);
            $("#prompt1").html(result.prompt);
            $("#sex1").val(result.sex);
            if(result.avatar==null||result.avatar==""){

            }
            else {

                $("#avatar1").attr("src","/"+result.avatar);
            }
        }
    })
}
function deleteUser(id) {
    var url=baseUrl+"/user/"+id;
    $.ajax({url:url,type:"DELETE",async:true,success:function (result) {
        alert("删除成功");
            window.location.reload();
        }})
}
function validateCheckCode() {
    var username=$("#userName").html();
    var email= $("#email").val();
    var checkCode= $("#emailCheckCodeInput").val();
    var url=baseUrl+"/validateCheckCode";
    debugger;
    $.ajax({url:url,type:"POST",data:{userId:$.cookie("userId"),checkCode:checkCode,email:email},async:true,success:function (result) {
            if(result.msg=="success"){
                debugger;
                $("#emailCheckCode").show();
              alert("验证成功");

            }
            else {
                alert("邮箱验证失败")
            }
        }})
}
function getEmailCheckCode111() {
    var email= $("#email").val();
    var reg =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    debugger;
    if(reg.test(email)||email==""){

    }
    else {
        alert("请输入正确格式的邮箱");
        return ;
    }
    $("#emailCheckCode").show();
    var username=$("#userName").html();

    var url=baseUrl+"/getCheckCode?userId="+$.cookie("userId")+"&email="+email;
    $.ajax({url:url,type:"GET",async:true,success:function (result) {
        debugger;
        }})
}
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");

    var r = window.location.search.substr(1).match(reg);
    debugger;
    if(r != null) return decodeURI(r[2]);

    return null;
}

function toAClick(id) {
    i=" ../pages/item.html?id="+id;
    window.location.href=i

}
function updateInfo1() {
    debugger;
    var user1=deepCopy(user);
    var id=GetQueryString("id");
    user1.username=$("#userName1").html();
    user1.realname=$("#realName1").val();
    user1.tel=$("#tel1").val();
    user1.sex=$("#sex1").val();
     user1.email=$("#email1").val();
    user1.location=$("#location1").val();
    user1.age=$("#age1").val();
    user1.prompt=$("#prompt1").html();
    user1.id=id;
    user1.avatar1=$("#avatar1").attr("src");
    var res=validateInputData(user1);
    if(res.msg=="success"){

    }
    else{
        alert(res.msg);
        return;
    }
    var i=JSON.stringify(user1);
    debugger;
    $.ajax({url:baseUrl+"/user",type:"POST",data:i,success:function (result) {
        debugger;
            debugger;
            alert("修改成功");
            var a=result;
            debugger;
            $("#realName1").val(result.realname);
            $("#age1").val(result.age);
            $("#sex1").val(result.sex);
            $("#location1").val(result.location);
            $("#tel1").val(result.tel);
            $("#email1").val(result.email);
            $("#prompt1").val(result.prompt);
        },contentType:'application/json;charset=utf-8'})

}
function getType(obj){
    if(Object.prototype.toString.call(obj)=='[object Object]'){
        return 'Object';
    }else if(Object.prototype.toString.call(obj)=='[object Array]'){
        return 'Array';
    }else{
        return 'nomal';
    }
}

function deepCopy(obj) {
    if (getType(obj)=='nomal') {
        return obj ;
    }else{
        var newObj = getType(obj)=='Object'?{}:[];
        for(var key in obj){
            if(obj.hasOwnProperty(key)){
                newObj[key] = deepCopy(obj[key]) ;
            }
        }
    }
    return newObj;
}
function validateInputData(obj) {
    debugger;
    var   regular=/^(?:[1-9][0-9]?|1[01][0-9]|120)$/;
    debugger;
    if(regular.test(obj.age)||obj.age==""){

    }
    else {
        return {"msg":"请输入正确的年龄"};
    }
    var reg =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    debugger;
    if(reg.test(obj.email)||obj.email==""){

    }
    else {
        return {"msg":"请输入正确格式的邮箱"};
    }
    var telReg=/^1[2,3,4,5,6,7,8,9][0-9]{9}$/;
    if(telReg.test(obj.tel)||obj.tel==""){

    }
    else {
        return{"msg":"请输入正确的手机号"};
    }
    return {"msg":"success"}
}
function errorDeny() {
    setTimeout(function () {
        window.location.href=baseUrl+"/pages/index.html";
    },3000)

}
function denyEnter() {
    debugger;
    $("input").keypress(function (e) {
        debugger;
            if(e.keyCode==13)
                e.preventDefault();
        })
}
function updateAvatar() {
    $("#avatar").attr("src", "assets/img/avatar/" + $.cookie('avatar'));
    $("#userIdName").html("用户"+$.cookie('userId'));
    $("#avatar2").attr("src", "assets/img/avatar/" + $.cookie('avatar'));
    mySpaceInfo();
}