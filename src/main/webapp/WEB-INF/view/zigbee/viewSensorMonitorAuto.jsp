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
                    htmlinit += '<td><img src="/resources/images/zigbee/icon_lamp_ON.jpg" id="'+picname+'"><span>'+data[i].name+'</span><span>光照:'+data[i].lightData+'</span><span>温度:'+data[i].temperatureData+'</span><br></td>';
                } else {
                    htmlinit += '<td><img src="/resources/images/zigbee/icon_lamp_OFF.jpg" id="'+picname+'"><span>'+data[i].name+'</span><span>光照:'+data[i].lightData+'</span><span>温度:'+data[i].temperatureData+'</span><br></td>';
                }
                if (i%2 == 1) {
                    htmlinit += '</tr>';
                }
                picname = "pic";
            }
            $('#left-nav').html(htmlinit);
        });
    })

    setTimeout('reload()',1000);
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
                    htmlinit += '<td><img src="/resources/images/zigbee/icon_lamp_ON.jpg" id="'+picname+'"><span>'+data[i].name+'</span><span>光照:'+data[i].lightData+'</span><span>温度:'+data[i].temperatureData+'</span><br></td>';
                } else {
                    htmlinit += '<td><img src="/resources/images/zigbee/icon_lamp_OFF.jpg" id="'+picname+'"><span>'+data[i].name+'</span><span>光照:'+data[i].lightData+'</span><span>温度:'+data[i].temperatureData+'</span><br></td>';
                }
                if (i%2 == 1) {
                    htmlinit += '</tr>';
                }
                picname = "pic";
            }
            $('#left-nav').html(htmlinit);
        });
        setTimeout('reload()',1000);
    }
    </script>
</body>
</html>