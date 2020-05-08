var baseUrl = "http://localhost:8091";
var mayLikeItemList;
var latestItemList;
var layer;
var rate
layui.use('carousel', function(){
    var carousel = layui.carousel;
    carousel.render({
        elem: '#test1'
        ,width: '100%' //设置容器宽度
        ,arrow: 'always' //始终显示箭头
        //,anim: 'updown' //切换动画方式
    });
});
layui.use('layer', function(){
    layer = layui.layer;


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
function getLatestItem() {
    youLike();
    $.ajax({
        url: baseUrl + "/item/latest",
        type: "GET",
        success: function (result) {
            console.log(result);
            latestItemList = result.data;
            for (var i = 0; i < latestItemList.length; i++) {
                var temp = latestItemList[i];
                var imgUrl = "assets/img/movice/"+temp.cover+".jpg";
                var itemName = temp.moviceName;
                var  releaseDate = temp.releaseDate.split(" ")[0];
                $("#latestItem").append("<div class=\"card col-2\" style=\"width: 18rem;margin-right: 1%;margin-left:1%;\">\n" +
                    "  <img src=" +imgUrl+
                    " class=\"card-img-top\" alt=\"...\">\n" +
                    "  <div class=\"card-body\">\n" +
                    "   <div><span class=\"card-title\">" +itemName+
                    "</span>\n" +
                    "    <span style=\"float: right\" class=\"card-title\">" +releaseDate+
                    "</span></div>\n"+
                    "    <button onclick='showLatestRating(this)' class=\"btn btn-primary\">详情</button>\n" +
                    "  <span style=\"display:none\">" +i+
                    "</span>\n" +
                    "  </div>\n" +
                    "</div>");
            }
        }
    })
}
function youLike() {
    $.ajax({
        url: baseUrl + "/item/mayLike?userId="+$.cookie("userId"),
        type: "GET",
        success: function (result) {
            console.log(result);
            mayLikeItemList = result.data;
            for (var i = 0; i < latestItemList.length; i++) {
                var temp = mayLikeItemList  [i];
                var imgUrl = "assets/img/movice/"+temp.cover+".jpg";
                var itemName = temp.moviceName;
                var  releaseDate = temp.releaseDate.split(" ")[0];
                $("#mayLike").append("<div class=\"card col-2\" style=\"width: 18rem;margin-right: 1%;margin-left:1%;\">\n" +
                    "  <img src=" +imgUrl+
                    " class=\"card-img-top\" alt=\"...\">\n" +
                    "  <div class=\"card-body\">\n" +
                    "   <div><span class=\"card-title\">" +itemName+
                    "</span>\n" +
                    "    <span style=\"float: right\" class=\"card-title\">" +releaseDate+
                    "</span></div>\n"+
                    "    <button onclick='showYouLikeRating(this)' class=\"btn btn-primary\">详情</button>\n" +
                    "  <span style=\"display:none\">" +i+
                    "</span>\n" +
                    "  </div>\n" +
                    "</div>");
            }
        }
    })
}
function showYouLikeRating(btn) {
    console.log("进入");
    var index = $(btn).next()[0].innerHTML;
    var itemEntity = latestItemList[index];
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
        "                                <h3>名称:<span>" +itemName+
        "</span></h3>\n" +
        "                                <h3>上映日期:<span>" +releaseDate+
        "</span></h3>\n" +
        "                                <h3>IMBD链接:<span>" +url+
        "</span></h3>\n" +
        "                                <div id=\"rating\">\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>",
        area: ['900px', '450px']
    });
    //渲染
    var ins1 = rate.render({
        elem: '#rating',
        half:true,
        choose:function (value) {
            setPreference(itemEntity,value)
        }
    });
}
function showLatestRating(btn) {
    console.log("进入");
    var index = $(btn).next()[0].innerHTML;
    var itemEntity = latestItemList[index];
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
        "                                <h3>名称:<span>" +itemName+
        "</span></h3>\n" +
        "                                <h3>上映日期:<span>" +releaseDate+
        "</span></h3>\n" +
        "                                <h3>IMBD链接:<span>" +url+
        "</span></h3>\n" +
        "                                <div id=\"rating\">\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>",
        area: ['900px', '450px']
    });
    //渲染
    var ins1 = rate.render({
        elem: '#rating',
        half:true,
        choose:function (value) {
            setPreference(itemEntity,value)
        }
    });
}
layui.use('rate', function(){
     rate = layui.rate;

});
function setPreference(itemEntity,value) {
    var userId = $.cookie("userId");
    var itemId = itemEntity.itemId;
    console.log(itemId);
    $.ajax({
        url: baseUrl + "/item/rating",
        type: "POST",
        data:{userId:userId,itemId:itemId,preference:value},
        success: function (result) {
            if (result.code==200)
                alert("评分成功");
        }
    })
}
