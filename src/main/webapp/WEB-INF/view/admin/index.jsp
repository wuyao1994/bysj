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
    <!--     <form style="color: white; margin-top: 200px;" method="post" action="/admin/login" class="form-horizontal"> -->
    <!--         <div style="margin: 0px auto 15px 550px; border-top-width: 0px;" class="form-group"> -->
    <!--             <label class="col-sm-2 control-label" for="j_username">Username</label> -->
    <!--             <div class="col-sm-10"> -->
    <!--                 <input type="Username" style="width: 180px;" placeholder="Username" id="j_username" class="form-control" name="j_username"> -->
    <!--             </div> -->
    <!--         </div> -->
    <!--         <div style="margin: 0px auto 15px 550px;" class="form-group"> -->
    <!--             <label class="col-sm-2 control-label" for="j_password">Password</label> -->
    <!--             <div class="col-sm-10"> -->
    <!--                 <input type="password" style="width: 180px;" placeholder="Password" id="j_password" class="form-control" name="j_password"> -->
    <!--             </div> -->
    <!--         </div> -->
    <!--         <div style="margin: 0 auto;" class="form-group"> -->
    <!--             <div class="col-sm-offset-2 col-sm-10" style="margin-left: 145px; padding-left: 0px; padding-right: 0px;"> -->
    <!--                 <button style="background-color: rgb(96, 176, 68); border-width: 0px; width: 150px; padding-left: 11px; margin-left: 5px;" class="btn btn-default" type="submit">Sign in</button> -->
    <!--             </div> -->
    <!--         </div> -->
    <!--         <p id="message"></p> -->
    <!--     </form> -->
    <form class="form-horizontal" style="color: white; margin-top: 200px;" action="/admin/login" method="post">
        <div class="form-group ">
            <label for="j_username" class="col-sm-2 control-label col-sm-offset-3">Username</label>
            <div class="col-sm-2">
                <input type="Username" name="j_username" class="form-control" id="j_username" placeholder="Username">
            </div>
        </div>
        <div class="form-group ">
            <label for="j_password" class="col-sm-2 control-label col-sm-offset-3">Password</label>
            <div class="col-sm-2">
                <input type="password" name="j_password" class="form-control" id="j_password" placeholder="Password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-5 col-sm-3">
                <button type="submit" class="col-sm-5 btn btn-default">Sign in</button>
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