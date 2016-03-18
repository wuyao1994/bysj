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
    <table>
        <tr>
            <td><img src="/resources/images/zigbee/icon_lamp_OFF.jpg" id="pic1"> <span>终端节点1</span><span>光照</span>
                <button class="btn btn-default" id = "led1" type="submit" onclick="led1()">ON</button></td>
            <td><img src="/resources/images/zigbee/icon_lamp_OFF.jpg" id="pic2"> <span>终端节点2</span><span>光照</span>
                <button class="btn btn-default" id = "led2" type="submit" onclick="led2()">ON</button></td>
        </tr>
        <tr>
            <td><img src="/resources/images/zigbee/icon_lamp_OFF.jpg" id="pic3"> <span>终端节点3</span><span>光照</span>
                <button class="btn btn-default" id = "led3" type="submit"onclick="led3()">ON</button></td>
            <td><img src="/resources/images/zigbee/icon_lamp_OFF.jpg" id="pic4"> <span>终端节点4</span><span>光照</span>
                <button class="btn btn-default" id = "led4" type="submit"onclick="led4()">ON</button></td>
        </tr>
    </table>
    <script type="text/javascript">
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
    </script>
</body>
</html>