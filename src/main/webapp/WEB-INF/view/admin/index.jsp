<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html style="margin-bottom: 0px;">
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>登录</title>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<script src="/resources/scripts/jquery-1.7.2.min.js"></script>
<script src="/resources/scripts/bootstrap.min.js"></script>
</head>
<body background="/resources/images/icon1.jpg" style="text-align: center;">
    <h1 style="color: white;">基于zigbee的智能楼宇电力节能管理系统</h1>
    <form class="form-horizontal" style="color: white; margin-top: 200px;" action="/admin/login" method="post">
        <div class="form-group col-sm-9">
            <label for="j_username" class="col-sm-2 control-label col-sm-offset-5">Username</label>
            <div class="col-sm-2">
                <input type="Username" name="j_username" class="form-control" id="j_username" placeholder="Username">
            </div>
        </div>
        <div class="form-group col-sm-9">
            <label for="j_password" class="col-sm-2 control-label col-sm-offset-5">Password</label>
            <div class="col-sm-2">
                <input type="password" name="j_password" class="form-control" id="j_password" placeholder="Password">
            </div>
        </div>
        <div class="form-group col-sm-9">
            <div class="col-sm-offset-7 col-sm-3">
                <button type="submit" class="col-sm-5 btn btn-default" style="background-color: rgb(96, 176, 68);border-top-width: 0px;border-right-width: 0px;border-bottom-width: 0px;border-left-width: 0px;">Sign in</button>
            </div>
        </div>
        <p id="message"></p>
    </form>
    <script type="text/javascript">
        $(function() {
            var result = "${result}";
            if (result != "") {
                $("#message").html("用户不存在或密码错误！");
            }
        })
    </script>
</body>
</html>