<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page isELIgnored ="false" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>编辑系统用户</title>
<link href="/resources/css/ext4.2.1/ext-theme-classic-all.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div style="padding: 40px">
        <form id="userForm">
            <table class="myTable">
                <tr>
                    <td class="tableTitle"><label class="myLabel" for="userName">账号名称:</label></td>
                    <td align="left"><input class="myInput" name="userName" id="userName" type="text" value=""/></td>
                    <td class="tableTitle"><label class="myLabel" for="userPass" >密码:</label></td>
                    <td align="left"><input  type="password" class="myInput" name="userPass" id="userPass" type="text" value=""/></td>
                </tr>
                <tr>
                    <td class="tableTitle"><label class="myLabel" for="email">邮箱:</label></td>
                    <td align="left"><input class="myInput" name="email" id="email" type="text" value=""/></td>
                    <td class="tableTitle"><label class="myLabel" for="active">是否可用:</label></td>
                    <td class="tableValue">
                        <label><input class="myRadio" name="active" type="radio" value="true"/>是</label>
                        <label><input class="myRadio" name="active" type="radio" value="false"/>否</label>
                    </td>
                </tr>
                <tr>
                    <td class="tableTitle"><label class="myLabel" for="role" >角色:</label></td>
                    <td align="left" colspan="3" id="rolesCell">
                        <label><input type="checkbox" class="myCheckBox"/></label>
                    </td>
                </tr>
            </table>
            <input type="hidden" id="userId" name="userId" value=""/>
        </form>
        <input type="button" class="btnSubmit" onclick="sp_save()" value="保存" />
        <input type="button" class="btnSubmit" onclick="sp_quit()" value="取消" />
    </div>

    <script src="/resources/scripts/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="/resources/scripts/ext4.2.1/ext-all.js" type="text/javascript" ></script>
    <script type="text/javascript">
        var userId = "${userId}";
        $(function(){
            initAllAdminRoles();
        });
        function initAdminUserInfo(userId){
            $.post('/admin/system/getAdminUserInfo',
                [
                    { name: 'userId', value: userId }
                ],
                function(result) {
                    $('#userId').val(result.userId);
                    $('#userName').val(result.userName);
                    $('#userName').attr("readOnly", true);
                    $('#userPass').val(result.userPass);
                    $('#email').val(result.email);
                    if(result.active){
                        $('input[name=active][value=true]').attr('checked', true);
                    }else{
                        $('input[name=active][value=false]').attr('checked', true);
                    }
                    for(var i=0; i<result.roles.length; i++){
                        $('input[name=role][value='+result.roles[i].no+']').attr('checked', true);
                    }
                }
            );
        }
        function initAllAdminRoles(){
            $.post('/admin/system/getAllAdminRoles',
                [],
                function(result) {
                    if(result){
                        var roleHtml = '';
                        for(var i=0; i<result.length; i++){
                            roleHtml += '<label><input type="checkbox" name="role" class="myCheckBox" value="'+result[i].no+'"/>'+result[i].name+'</label>';
                        }
                        $('#rolesCell').html(roleHtml);
                        if(userId){
                            initAdminUserInfo(userId);
                        }
                    }
                }
            );
        }
        function sp_quit(){
            location.href = "/admin/system/viewAdminUsers";
        }
        function sp_save(){
            if(checkData()){
                var user = {};
                user.userId = $('#userId').val();
                user.userName = $('#userName').val();
                user.userPass = $('#userPass').val();
                user.email = $('#email').val();
                user.active = $('input:checked[name=active]').val();

                var roles = [];
                $('input[name=role]:checked').each(function(){
                    var role = {};
                    role.no = $(this).val();
                    roles.push(role);
                });
                $.post('/admin/system/addOrUpdateAdmin',
                    [
                        { name: 'jParam', value: JSON.stringify(user) },
                        { name: 'roles', value: JSON.stringify(roles) }
                    ],
                    function(result){
                    if(result.code == 0){
                        Ext.Msg.alert("提示", "保存成功", function(result){
                            if(result){
                                window.location = "/admin/system/viewAdminUsers";
                            }
                        });
                    }else{
                        Ext.Msg.alert("提示", '保存失败:'+result.message);
                    }
                });
            }
        }
        function checkData(){
            if($("#userName").val() == ""){
                Ext.Msg.alert("提示", '请至少输入一个名称');
                return false;
            }
            return true;
        }
    </script>
</body>
</html>