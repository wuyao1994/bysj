<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改密码</title>
<link href="/resources/css/ext4.2.1/ext-theme-classic-all.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div id="form-div">
        <form id="userForm">
            <input type="hidden" id="no" name="no" value="${no}" />
            <table class="myTable">
                <tr>
                    <td class="tableTitle"><label class="myLabel" for="oldPassword">原密码:</label></td>
                    <td class="tableValue">
                        <input class="myInput" name="oldPassword" id="oldPassword" type="password" value=""/>
                    </td>
                    <td class="tableTitle"></td>
                    <td class="tableValue"></td>
                </tr>
                <tr>
                    <td class="tableTitle"><label class="myLabel" for="newPassword">新密码:</label></td>
                    <td class="tableValue">
                        <input class="myInput" name="newPassword" id="newPassword" type="password" value=""/>
                    </td>
                    <td class="tableTitle"></td>
                    <td class="tableValue"></td>
                </tr>
                <tr>
                    <td class="tableTitle"><label class="myLabel" for="newPasswordRep">新密码（重输）:</label></td>
                    <td class="tableValue">
                        <input class="myInput" name="newPasswordRep" id="newPasswordRep" type="password" value=""/>
                    </td>
                    <td class="tableTitle"></td>
                    <td class="tableValue"></td>
                </tr>
            </table>
        </form>
        <input type="button" class="btnSubmit" onclick="submitPasswordInfo()" value="保存" />
    </div>
    <script src="/resources/scripts/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="/resources/scripts/ext4.2.1/ext-all.js" type="text/javascript" ></script>
    <script type="text/javascript">
    function submitPasswordInfo(){
        if($("#newPassword").val()==""||$("#newPasswordRep").val()==""){
            Ext.Msg.alert("提示", "请输入新密码:"+result.message);
            return false;
        }
        if($("#newPassword").val()!=$("#newPasswordRep").val()){
            Ext.Msg.alert("提示", "请输入相同的新密码确认:"+result.message);
            return false;
        }
        $.ajax({
            url : "/admin/system/updateAdminPassword",
            type : "post",
            data :{
                oldPassword : $("#oldPassword").val(),
                newPassword : $("#newPassword").val()
            },
            success : function(result){
                if(result.code == 0){
                    Ext.Msg.alert("提示", "修改密码成功", function(result){
                        if(result){
                            window.location = "/admin/system/updatePassword";
                        }
                    });
                } else {
                    Ext.Msg.alert("提示", "保存失败:"+result.message);
                }
            }
        });
    }
    </script>
</body>
</html>