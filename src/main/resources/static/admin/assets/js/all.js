var baseUrl = "http://localhost:8091";
var allItem;
var rate;
var laypage;
var totalPage;
var startYear;
var endYear;
var moviceName;
layui.use('rate', function(){
    rate = layui.rate;
});
layui.use('layer', function(){
    layer = layui.layer;
});
layui.use('laypage', function(){
    laypage = layui.laypage;
});
function logout() {
    $.ajax({
        url: baseUrl + "/logout",
        type: "POST",
        success: function (result) {
            window.location.href = 'http://localhost:8091/admin/login.html';
        }
    })
}
function searchByName() {
    updateAvatar();
    var moviceName = $("#searchInput").val();
    var pageIndex = 0;
    var size = 10;
    $.ajax({
        url: baseUrl + "/item/movice?moviceName="+moviceName+"&pageIndex="+pageIndex+"&size="+size,
        type: "GET",
        success: function (result) {
            allItem = result.data.list;
            totalPage = result.data.totalPage;
            laypage.render({
                elem: 'pagenator',
                count: result.data.totalPage,
                jump: function (obj, first) {
                    var currIndex = obj.curr;
                    var size = obj.limit;
                    if(!first){
                        searchName(currIndex,size);
                    }
                }
            });
            var el = document.getElementById('searchItems');
            var childs = el.childNodes;
            if (childs!=undefined&&childs!=null&&childs.length>0){
                for(var i = childs .length - 1; i >= 0; i--) {
                    el.removeChild(childs[i]);
                }
            }
            for (var i = 0; i < allItem.length; i++) {
                var temp = allItem[i];
                var imgUrl = "assets/img/movice/"+temp.cover+".jpg";
                var itemName = temp.moviceName;
                var  releaseDate = temp.releaseDate.split(" ")[0];
                var preference = temp.preference;
                $("#searchItems").append("<div class=\"card col-2\" style=\"width: 18rem;margin-right: 1%;margin-left:1%;\">\n" +
                    "  <img src=" +imgUrl+
                    " class=\"card-img-top\" alt=\"...\">\n" +
                    "  <div class=\"card-body\">\n" +
                    "   <div><span class=\"card-title\">" +itemName+
                    "</span>\n" +
                    "    <span style=\"float: right\" class=\"card-title\">" +releaseDate+
                    "</span></div>\n"+
                    "    <div class=\"row am-cf\" style=\"width:100%\"><div class=\"am-u-sm-12 am-u-md-4\"><button onclick='showYouLikeRating(this)' class=\"btn btn-primary\">详情</button>" +
                    "  <span style=\"display:none\">" +i+
                    "</span>\n" +
                    "</div><div style=\"font-size: 15px;margin-top: 2px\" class=\"am-u-sm-12 am-u-md-8 \"><span>推荐指数：</span><span>" + preference.toFixed(2)+

                    "  </div>\n" +
                    "</div>");
            }
        }
    })
}
function showAllRating(btn) {
    var index = $(btn).next()[0].innerHTML;
    var itemEntity = allItem[index];
    var imgUrl = "assets/img/movice/"+itemEntity.cover+".jpg";
    var itemName = itemEntity.moviceName;
    var  releaseDate = itemEntity.releaseDate.split(" ")[0];
    var url = itemEntity.url;
    layer.open({
        type: 1,
        content: "                        <div  class=\"row\">\n" +
        "                            <div class=\"col-lg-4\">\n" +
        "                                <img style=\"width: 270px;height: 360px\" src=" +imgUrl+
        ">\n" +
        "                            </div>\n" +
        "                            <div class=\"col-lg-5\">\n" +
        "                                <h3>名称:   <span>" +itemName+
        "</span></h3>\n" +
        "                                <h3>上映日期: <span>" +releaseDate+
        "</span></h3>\n" +
        "                                <h3>IMBD链接: <span>" +url+
        "</span></h3>\n" +
        "                                <div id=\"preference\">\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>",
        area: ['900px', '450px']
    });
    //渲染
    var ins1 = rate.render({
        elem: '#preference',
        half:true,
        choose:function (value) {
            setPreference(itemEntity,value)
        }
    });
}
function setPreference(itemEntity,value) {
    var userId = $.cookie("userId");
    var itemId = itemEntity.itemId;
    console.log(itemId);
    $.ajax({
        url: baseUrl + "/item/preference",
        type: "POST",
        data:{userId:userId,itemId:itemId,preference:value},
        success: function (result) {
            if (result.code==200)
                alert("评分成功");
        }
    })
}
function searchItemByYear(end,start) {
    startYear = start;
    endYear = end;
    var pageIndex = 0;
    var size = 10;
    $.ajax({
        url: baseUrl + "/item/moviceYear?startYear="+startYear+"&endYear="+endYear+"&pageIndex="+pageIndex+"&size="+size,
        type: "GET",
        success: function (result) {
            allItem = result.data.list;
            totalPage = result.data.totalPage;
            laypage.render({
                elem: 'pagenator',
                count: result.data.totalPage,
                jump: function (obj, first) {
                    var currIndex = obj.curr;
                    var size = obj.limit;
                    if(!first){
                      search(currIndex,size);
                    }
                }
            });
            var el = document.getElementById('searchItems');
            var childs = el.childNodes;
            if (childs!=undefined&&childs!=null&&childs.length>0){
                for(var i = childs .length - 1; i >= 0; i--) {
                    el.removeChild(childs[i]);
                }
            }
            for (var i = 0; i < allItem.length; i++) {
                var temp = allItem[i];
                var imgUrl = "assets/img/movice/"+temp.cover+".jpg";
                var itemName = temp.moviceName;
                var  releaseDate = temp.releaseDate.split(" ")[0];
                var preference = temp.preference;
                $("#searchItems").append("<div class=\"card col-2\" style=\"width: 18rem;margin-right: 1%;margin-left:1%;\">\n" +
                    "  <img src=" +imgUrl+
                    " class=\"card-img-top\" alt=\"...\">\n" +
                    "  <div class=\"card-body\">\n" +
                    "   <div><span class=\"card-title\">" +itemName+
                    "</span>\n" +
                    "    <span style=\"float: right\" class=\"card-title\">" +releaseDate+
                    "</span></div>\n"+
                    "    <div class=\"row am-cf\" style=\"width:100%\"><div class=\"am-u-sm-12 am-u-md-4\"><button onclick='showYouLikeRating(this)' class=\"btn btn-primary\">详情</button>" +
                    "  <span style=\"display:none\">" +i+
                    "</span>\n" +
                    "</div><div style=\"font-size: 15px;margin-top: 2px\" class=\"am-u-sm-12 am-u-md-8 \"><span>推荐指数：</span><span>" + preference.toFixed(2)+

                    "  </div>\n" +
                    "</div>");
            }
        }
    })
}
function search(pageIndex,size) {
    $.ajax({
        url: baseUrl + "/item/moviceYear?startYear="+startYear+"&endYear="+endYear+"&pageIndex="+pageIndex+"&size="+size,
        type: "GET",
        success: function (result) {
            allItem = result.data.list;
            var el = document.getElementById('searchItems');
            var childs = el.childNodes;
            if (childs!=undefined&&childs!=null&&childs.length>0){
                for(var i = childs .length - 1; i >= 0; i--) {
                    el.removeChild(childs[i]);
                }
            }
            for (var i = 0; i < allItem.length; i++) {
                var temp = allItem[i];
                var imgUrl = "assets/img/movice/"+temp.cover+".jpg";
                var itemName = temp.moviceName;
                var  releaseDate = temp.releaseDate.split(" ")[0];
                var preference = temp.preference;
                $("#searchItems").append("<div class=\"card col-2\" style=\"width: 18rem;margin-right: 1%;margin-left:1%;\">\n" +
                    "  <img src=" +imgUrl+
                    " class=\"card-img-top\" alt=\"...\">\n" +
                    "  <div class=\"card-body\">\n" +
                    "   <div><span class=\"card-title\">" +itemName+
                    "</span>\n" +
                    "    <span style=\"float: right\" class=\"card-title\">" +releaseDate+
                    "</span></div>\n"+
                    "    <div class=\"row am-cf\" style=\"width:100%\"><div class=\"am-u-sm-12 am-u-md-4\"><button onclick='showYouLikeRating(this)' class=\"btn btn-primary\">详情</button>" +
                    "  <span style=\"display:none\">" +i+
                    "</span>\n" +
                    "</div><div style=\"font-size: 15px;margin-top: 2px\" class=\"am-u-sm-12 am-u-md-8 \"><span>推荐指数：</span><span>" + preference.toFixed(2)+
                    "  </div>\n" +
                    "</div>");
            }
        }
    })
}
function  searchName(pageIndex,size) {
    var moviceName = $("#searchInput").val();
    $.ajax({
        url: baseUrl + "/item/movice?moviceName="+moviceName+"&pageIndex="+pageIndex+"&size="+size,
        type: "GET",
        success: function (result) {
            allItem = result.data.list;
            var el = document.getElementById('searchItems');
            var childs = el.childNodes;
            if (childs!=undefined&&childs!=null&&childs.length>0){
                for(var i = childs .length - 1; i >= 0; i--) {
                    el.removeChild(childs[i]);
                }
            }
            for (var i = 0; i < allItem.length; i++) {
                var temp = allItem[i];
                var imgUrl = "assets/img/movice/"+temp.cover+".jpg";
                var itemName = temp.moviceName;
                var  releaseDate = temp.releaseDate.split(" ")[0];
                var preference = temp.preference;
                $("#searchItems").append("<div class=\"card col-2\" style=\"width: 18rem;margin-right: 1%;margin-left:1%;\">\n" +
                    "  <img src=" +imgUrl+
                    " class=\"card-img-top\" alt=\"...\">\n" +
                    "  <div class=\"card-body\">\n" +
                    "   <div><span class=\"card-title\">" +itemName+
                    "</span>\n" +
                    "    <span style=\"float: right\" class=\"card-title\">" +releaseDate+
                    "</span></div>\n"+
                    "    <div class=\"row am-cf\" style=\"width:100%\"><div class=\"am-u-sm-12 am-u-md-4\"><button onclick='showYouLikeRating(this)' class=\"btn btn-primary\">详情</button>" +
                    "  <span style=\"display:none\">" +i+
                    "</span>\n" +
                    "</div><div style=\"font-size: 15px;margin-top: 2px\" class=\"am-u-sm-12 am-u-md-8 \"><span>推荐指数：</span><span>" + preference.toFixed(2)+
                    "  </div>\n" +
                    "</div>");
            }
        }
    })
}
function updateAvatar() {
    $("#avatar").attr("src", "assets/img/avatar/" + $.cookie('avatar'));
    $("#userIdName").html("用户"+$.cookie('userId'));
}