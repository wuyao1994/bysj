<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<script src="/resources/scripts/jquery-1.7.2.min.js"></script>
<script src="/resources/scripts/bootstrap.min.js"></script>
<link rel="stylesheet" href="/resources/css/zigbee.css" />
<title>节点监测</title>
<!-- 手动模式 -->
</head>
<body class="viewZigbee">
    <table id="left-nav">
    </table>
    <script type="text/javascript">
    $(function() {
        $.post('/zigbee/getSensorInfoList', [],function(data, status) {
            var picname = "pic";
            var htmlinit="";
            for (i = 0; i< data.length; i++) {
                if (i%2 == 0) {
                    htmlinit += '<tr>';
                }
                picname = picname+data[i].id;
                if (data[i].statu == 1) {
                    htmlinit += '<td><span>'+data[i].name2+'</span><img src="/resources/images/zigbee/icon_lamp_ON.jpg" id="'+picname+'"><span>'+data[i].name+'</span><span>光照:'+data[i].lightData+'</span><span>温度:'+data[i].temperatureData+'</span><br><button class="btn btn-default" id = "'+data[i].name+ '" type="submit" onclick="'+data[i].name+'()">OFF</button></td>';
                } else {
                    htmlinit += '<td><span>'+data[i].name2+'</span><img src="/resources/images/zigbee/icon_lamp_OFF.jpg" id="'+picname+'"><span>'+data[i].name+'</span><span>光照:'+data[i].lightData+'</span><span>温度:'+data[i].temperatureData+'</span><br><button class="btn btn-default" id = "'+data[i].name+ '" type="submit" onclick="'+data[i].name+'()">ON</button></td>';
                }
                if (i%2 == 1) {
                    htmlinit += '</tr>';
                }
                picname = "pic";
            }
            $('#left-nav').html(htmlinit);
        });
    })
    function led1() {
        if(document.getElementById("led1").innerHTML == "ON") {
            document.getElementById("led1").innerHTML="OFF";
//             $.post('/zigbee/changeStatu?id=1&statu=1');
                $.ajax({
                    url:'/zigbee/changeStatu',
                    data:{
                        id : 1,
                        statu : 1
                    },
                    type:'post'
                });
            var imgObj = document.getElementById("pic1");
            imgObj.src="/resources/images/zigbee/icon_lamp_ON.jpg";
        } else {
            document.getElementById("led1").innerHTML="ON";
            $.post('/zigbee/changeStatu?id=1&statu=0');
            var imgObj = document.getElementById("pic1");
            imgObj.src="/resources/images/zigbee/icon_lamp_OFF.jpg";
        }
    }
    function led2() {
        if(document.getElementById("led2").innerHTML == "ON") {
            document.getElementById("led2").innerHTML="OFF";
            $.post('/zigbee/changeStatu?id=2&statu=1');
            var imgObj = document.getElementById("pic2");
            imgObj.src="/resources/images/zigbee/icon_lamp_ON.jpg";
        } else {
            document.getElementById("led2").innerHTML="ON";
            $.post('/zigbee/changeStatu?id=2&statu=0');
            var imgObj = document.getElementById("pic2");
            imgObj.src="/resources/images/zigbee/icon_lamp_OFF.jpg";
        }
    }
    function led3() {
        if(document.getElementById("led3").innerHTML == "ON") {
            document.getElementById("led3").innerHTML="OFF";
            $.post('/zigbee/changeStatu?id=3&statu=1');
            var imgObj = document.getElementById("pic3");
            imgObj.src="/resources/images/zigbee/icon_lamp_ON.jpg";
        } else {
            document.getElementById("led3").innerHTML="ON";
            $.post('/zigbee/changeStatu?id=3&statu=0');
            var imgObj = document.getElementById("pic3");
            imgObj.src="/resources/images/zigbee/icon_lamp_OFF.jpg";
        }
    }
    function led4() {
        if(document.getElementById("led4").innerHTML == "ON") {
            document.getElementById("led4").innerHTML="OFF";
            $.post('/zigbee/changeStatu?id=4&statu=1');
            var imgObj = document.getElementById("pic4");
            imgObj.src="/resources/images/zigbee/icon_lamp_ON.jpg";
        } else {
            document.getElementById("led4").innerHTML="ON";
            $.post('/zigbee/changeStatu?id=4&statu=0');
            var imgObj = document.getElementById("pic4");
            imgObj.src="/resources/images/zigbee/icon_lamp_OFF.jpg";
        }
    }
    setTimeout('reload()',10000);
    function reload() {
        $.post('/zigbee/getSensorInfoList', [],function(data, status) {
            var picname = "pic";
            var htmlinit="";
            for (i = 0; i< data.length; i++) {
                if (i%2 == 0) {
                    htmlinit += '<tr>';
                }
                picname = picname+data[i].id;
                if (data[i].statu == 1) {
                    htmlinit += '<td><span>'+data[i].name2+'</span><img src="/resources/images/zigbee/icon_lamp_ON.jpg" id="'+picname+'"><span>'+data[i].name+'</span><span>光照:'+data[i].lightData+'</span><span>温度:'+data[i].temperatureData+'</span><br><button class="btn btn-default" id = "'+data[i].name+ '" type="submit" onclick="'+data[i].name+'()">OFF</button></td>';
                } else {
                    htmlinit += '<td><span>'+data[i].name2+'</span><img src="/resources/images/zigbee/icon_lamp_OFF.jpg" id="'+picname+'"><span>'+data[i].name+'</span><span>光照:'+data[i].lightData+'</span><span>温度:'+data[i].temperatureData+'</span><br><button class="btn btn-default" id = "'+data[i].name+ '" type="submit" onclick="'+data[i].name+'()">ON</button></td>';
                }
                if (i%2 == 1) {
                    htmlinit += '</tr>';
                }
                picname = "pic";
            }
            $('#left-nav').html(htmlinit);
        });
        setTimeout('reload()',10000);
    }
    
    setTimeout('changemode()',1000);
    function changemode() {
        $.post('/zigbee/changemode?mode=0');
    }
    </script>
</body>
</html>