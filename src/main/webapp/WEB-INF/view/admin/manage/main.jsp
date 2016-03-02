<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/admin.css" />
</head>
<body class="main-body" style="overflow: hidden" >
    <div id="main_header">
        <div class="header_left">
            <div class="left_part">
                <span class="vertical_center"></span>
                <img class="shift_icon" src="/resources/images/shift_down.png" title="显示/隐藏菜单栏"/>
            </div>
        </div>
        <div class="header_right">
            <div class="refresh_setcut" id="logout-div">
                <span class="vertical_center"></span>
                <div class="s_userinfo admin-show-hid">
                    <a href="#" style="vertical-align: middle; height: 15px; width: 15px;" title="当前用户">
                        <span class="vertical_center"></span>
                        <img id="adminHeadImage" src="/resources/images/user_icon.png" alt="" />
                        <span style="margin-right: 20px;"><sec:authentication property="principal.userName" /></span>
                    </a>
                    <div id="abs-admin">
                    <a href="/admin/logout">
                        <span class="vertical_center"></span>
                        <img class="loginOut_img" src="/resources/images/icon_qiut.png" /><span style="margin-right:10px;">退出</span>
                    </a>
                </div>
                </div>
            </div>
        </div>
    </div>
    <div id="left-nav">
    </div>
<!--     <div id="right-detail-div"> -->
<!--         <iframe frameborder="0" name="detail-frame" id="detail-frame" src="/admin/manage/home"></iframe> -->
<!--     </div> -->
    <div id="messageContent">
    </div>
    <script src="/resources/script/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        var widthWhole = document.body.offsetWidth;
        console.log(widthWhole);
        var $allMenus = null;
        $(function(){
            $(".level1-menu").live("click",function(){
                var target = $(this);
                if(target.hasClass("selected-menu")){
                    target.removeClass("selected-menu");
                    var menu = target.next(".menuUl");
                    menu.slideUp(200);
                } else {
                    target.addClass("selected-menu");
                    var menu = target.next(".menuUl");
                    menu.slideDown(200);
                }

                $(".level1-menu").each(function(){
                    if($(this).html() != target.html()){
                        $(this).removeClass("selected-menu");
                        $(this).next(".menuUl").slideUp(200);
                    }
                });
            });
            window.onresize = resizePage;
            resizePage();
            loadMenus();
        });
        function resizePage(){
            var detail_div_width = document.body.clientWidth - 200;
            $("#right-detail-div").width(detail_div_width+"px");
            var detail_div_height = document.body.clientHeight-70;
            $("#right-detail-div").height(detail_div_height+"px");
            $("#left-nav").height(detail_div_height+"px");
        }
        function loadMenus(){
            $.post('/admin/system/getAdminUserMenus',
                [],
                function(result){
                var $allMenus = new Array();
                for(var i=0; i<result.length; i++){
                    if(result[i].level == 1){
                        var index = -1;
                        for(var j=0; j<$allMenus.length; j++){
                            if($allMenus[j].menu.no == result[i].no){
                                index = j;
                                break;
                            }
                        }
                        if(index == -1){
                            var headMenu = {};
                            headMenu.menu = result[i];
                            headMenu.subMenus = [];
                            $allMenus.push(headMenu);
                        }
                    } else {
                        var index = -1;
                        for(var j=0; j<$allMenus.length; j++){
                            if($allMenus[j].menu.no == result[i].parentMenu.no){
                                index = j;
                                break;
                            }
                        }
                        if(index != -1){
                            $allMenus[index].subMenus.push(result[i]);
                        }
                    }
                }
                var htmlMenus = '';
                for(var i=0; i<$allMenus.length; i++){
                    var d="";
                    if(i==0)
                        d="/resources/images/combine.png";
                    if(i==1)
                        d="/resources/images/sys.png";
                    htmlMenus += '<div class="level1-menu"><span class="vertical_center"></span><img src="'+d+'"class="menueImg"/><span style="cursor:pointer;">'+$allMenus[i].menu.name+'</span><div class="menueRigtPrt"><span class="vertical_center"></span><div class="menu-arrow"/></div></div></div>';
                    htmlMenus +='<div class="menuUl">';
                    for(var j=0; j<$allMenus[i].subMenus.length; j++){
                        htmlMenus += '<a href="'+$allMenus[i].subMenus[j].url+'"target="detail-frame" id="menu'+j+i+'"class="level2-menu"><img src="/resources/images/admin/tree_3.png" style="width:15px;height:10px;margin-right:8px;" />'+$allMenus[i].subMenus[j].name+'</a>';
                    }
                    htmlMenus +='</div>'
                }
                $('#left-nav').html(htmlMenus);
            });
        }
        
        $(".shift_icon").click(function(){
            if($("#left-nav").css("display")=="none"){
                $("#left-nav").show('200');
                $("#right-detail-div").animate({width:widthWhole-200},'fast');
                var iframeC = window.frames[0].document;
                $(iframeC).find('td .highcharts-container').width(576);
            } else {
                $("#left-nav").hide('200');
                $("#right-detail-div").animate({width:widthWhole},'200');
            }
        })
    </script>
</body>
</html>