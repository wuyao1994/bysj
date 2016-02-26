<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="margin-bottom: 0px;">
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>登录</title>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<script src="/resources/script/jquery-1.7.2.min.js"></script>
<script src="/resources/script/bootstrap.min.js"></script>
</head>
<body background="/resources/image/icon1.jpg" style="text-align: center;">
    <h1 style="color: white;">基于ZigBee的楼宇电力节能管理系统</h1>
    <form style="color: white; margin-top: 200px;" method="post" action="/admin/login" class="form-horizontal">
        <div style="margin: 0px auto 15px 550px; border-top-width: 0px;" class="form-group">
            <label class="col-sm-2 control-label" for="inputUsername3">Username</label>
            <div class="col-sm-10">
                <input type="Username" style="width: 180px;" placeholder="Username" id="inputUsername3" class="form-control" name="j_username">
            </div>
        </div>
        <div style="margin: 0px auto 15px 550px;" class="form-group">
            <label class="col-sm-2 control-label" for="inputPassword3">Password</label>
            <div class="col-sm-10">
                <input type="password" style="width: 180px;" placeholder="Password" id="inputPassword3" class="form-control" name="j_password">
            </div>
        </div>
        <div style="margin: 0 auto;" class="form-group">
            <div class="col-sm-offset-2 col-sm-10" style="margin-left: 145px; padding-left: 0px; padding-right: 0px;">
                <button style="background-color: rgb(96, 176, 68); border-width: 0px; width: 150px; padding-left: 11px; margin-left: 5px;" class="btn btn-default" type="submit">Sign in</button>
            </div>
        </div>
    </form>
</body>
</html>