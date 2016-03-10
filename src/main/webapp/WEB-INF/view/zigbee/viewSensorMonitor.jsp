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
            <td><img src="/resources/images/zigbee/icon_lamp.png"> <span>节点1 光照：</span>
                <button class="btn btn-default" id = "led1" type="submit" onclick="led1()">NO</button></td>
            <td><img src="/resources/images/zigbee/icon_lamp.png"> <span>节点2 光照：</span>
                <button class="btn btn-default" id = "led2" type="submit" onclick="led2()">NO</button></td>
        </tr>
        <tr>
            <td><img src="/resources/images/zigbee/icon_lamp.png"> <span>节点3 光照：</span>
                <button class="btn btn-default" id = "led3" type="submit"onclick="led3()">NO</button></td>
            <td><img src="/resources/images/zigbee/icon_lamp.png"> <span>节点4 光照：</span>
                <button class="btn btn-default" id = "led4" type="submit"onclick="led4()">NO</button></td>
        </tr>
    </table>
    <script type="text/javascript">
    function led1() {
        if(document.getElementById("led1").innerHTML == "NO") {
            document.getElementById("led1").innerHTML="OFF";
        } else {
            document.getElementById("led1").innerHTML="NO";
        }
    }
    function led2() {
        if(document.getElementById("led2").innerHTML == "NO") {
            document.getElementById("led2").innerHTML="OFF";
        } else {
            document.getElementById("led2").innerHTML="NO";
        }
    }
    function led3() {
        if(document.getElementById("led3").innerHTML == "NO") {
            document.getElementById("led3").innerHTML="OFF";
        } else {
            document.getElementById("led3").innerHTML="NO";
        }
    }
    function led4() {
        if(document.getElementById("led4").innerHTML == "NO") {
            document.getElementById("led4").innerHTML="OFF";
        } else {
            document.getElementById("led4").innerHTML="NO";
        }
    }
    </script>
</body>
</html>