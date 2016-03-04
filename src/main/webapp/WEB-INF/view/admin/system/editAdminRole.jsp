<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page isELIgnored ="false" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>服务订单</title>
<link href="/resources/css/ext4.2.1/ext-theme-classic-all.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div id="form-div">
        <form id="userForm">
            <input type="hidden" id="no" name="no" value="${no}" />
            <table class="myTable">
                <tr>
                    <td class="tableTitle"><label class="myLabel" for="name">角色名称:</label></td>
                    <td class="tableValue">
                        <input class="myInput" name="name" id="name" type="text" value=""/>
                    </td>
                    <td class="tableTitle"><label class="myLabel" for="active">是否可用:</label></td>
                    <td class="tableValue">
                        <label><input class="myRadio" name="active" type="radio" value="true"/>是</label>
                        <label><input class="myRadio" name="active" type="radio" value="false"/>否</label>
                    </td>
                </tr>
                <tr>
                    <td class="tableTitle"><label class="myLabel" for="sequence">描述:</label></td>
                    <td class="tableValue" colspan="3">
                        <textarea id="description" name="description" rows="5" cols="60"></textarea>
                    </td>
                </tr>
            </table>
        </form>
        <div id="AdminGrant-grid"></div>
        <input type="button" class="btnSubmit" onclick="sp_save()" value="保存" />
        <input type="button" class="btnSubmit" onclick="sp_quit()" value="取消" />
    </div>

    <script src="/resources/scripts/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="/resources/scripts/ext4.2.1/ext-all.js" type="text/javascript" ></script>
    <script src="/resources/scripts/admin/viewAdminGrants.js" type="text/javascript"></script>
    <script type="text/javascript">
        Ext.onReady(function(){
            var no = '${no}';
            if(no){
                initAdminRole(no);
                loadRoleGrants(no);
            }else{
                loadRoleGrants(0);
            }
        });
        function initAdminRole(no){
            $.post('/admin/system/getAdminRole',
                    [
                        { name: 'roleNo', value: no }
                    ],
                    function(result) {
                        $('#no').val(result.no);
                        $('#name').val(result.name);
                        $('#description').val(result.description);
                        if(result.active){
                            $('input[name=active][value=true]').attr('checked', true);
                        }else{
                            $('input[name=active][value=false]').attr('checked', true);
                        }
                    }
                );
        }

        function sp_save(){
            if(checkData()){
                var role = {};
                role.no = $('#no').val();
                role.name = $('#name').val();
                role.description = $('#description').val();
                role.active = $('input:checked[name=active]').val();

                var grants = [];
                grantStore.each(function(record){
                    var data = record.data;
                    if(data.view || data.delete || data.update || data.create){
                        var grant = {};
                        grant.create = data.create;
                        grant.view = data.view;
                        grant.update = data.update;
                        grant.delete = data.delete;
                        grant.active = true;
                        var adminMenu = {};
                        adminMenu.no = data['adminMenu.no'];
                        grant.adminMenu = adminMenu;
                        grants.push(grant);
                    }
                });
                $.post('/admin/system/addOrUpdateAdminRole',
                    [
                        { name: 'jParam', value: JSON.stringify(role) },
                        { name: 'jGrant', value: JSON.stringify(grants) }
                    ],
                    function(result) {
                        if (result.code == 0){
                            Ext.Msg.alert("提示", "保存成功", function(result){
                                if(result){
                                    window.location = "/admin/system/viewAdminRoles";
                                }
                            });
                        } else {
                            Ext.Msg.alert("提示", "保存失败:"+result.message);
                        }
                    }
                );
            }
        }

        function checkData(){
            if($("#name").val() == ""){
                Ext.Msg.alert("提示", "菜单名称必填");
                return false;
            }
            return true;
        }
        function sp_quit(){
            location.href = "/admin/system/viewAdminRoles";
        }
    </script>
</body>
</html>