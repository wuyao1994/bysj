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
                    <td class="tableTitle"><label class="myLabel" for="name">菜单名称:</label></td>
                    <td class="tableValue">
                        <input class="myInput" name="name" id="name" type="text" value=""/>
                    </td>
                    <td class="tableTitle"><label class="myLabel" for="level" >菜单等级:</label></td>
                    <td class="tableValue">
                        <select class="mySelect" name="level" id="level">
                            <option value="1">1级</option>
                            <option value="2">2级</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="tableTitle"><label class="myLabel" for="url">链接:</label></td>
                    <td class="tableValue">
                        <input class="myInput" name="url" id="url" type="text" value=""/>
                    </td>
                    <td class="tableTitle"><label class="myLabel" for="parentMenuNo" >父级菜单:</label></td>
                    <td class="tableValue">
                        <input class="myInput" name="parentMenuNo" id="parentMenuNo" type="text" value=""/>
                        <input class="myInput" name="parentMenuName" id="parentMenuName" type="hidden" value=""/>
                    </td>
                </tr>
                <tr>
                    <td class="tableTitle"><label class="myLabel" for="sequence">菜单序号（排序）:</label></td>
                    <td class="tableValue">
                        <input class="myInput" name="sequence" id="sequence" type="text" value=""/>
                    </td>
                    <td class="tableTitle"><label class="myLabel" for="modelId">模块编号:</label></td>
                    <td class="tableValue">
                        <input class="myInput" name="modelId" id="modelId" type="text" value=""/>
                    </td>
                </tr>
                <tr>
                    <td class="tableTitle"><label class="myLabel" for="active">是否可用:</label></td>
                    <td class="tableValue">
                        <label><input class="myRadio" name="active" type="radio" value="true"/>是</label>
                        <label><input class="myRadio" name="active" type="radio" value="false"/>否</label>
                    </td>
                </tr>
            </table>
        </form>
        <input type="button" class="btnSubmit" onclick="sp_save()" value="保存" />
        <input type="button" class="btnSubmit" onclick="sp_quit()" value="取消" />
    </div>

    <script src="/resources/scripts/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="/resources/scripts/ext4.2.1/ext-all.js" type="text/javascript" ></script>
    <script type="text/javascript">
        $(function(){
            var no = '${no}';
            if(no){
                initService(no);
            }
        });
        function initService(no){
            $.post('/admin/system/getAdminMenu',
                    [
                        { name: 'menuNo', value: no }
                    ],
                    function(result) {
                        $('#no').val(result.no);
                        $('#name').val(result.name);
                        $('#url').val(result.url);
                        $('#level').val(result.level);
                        $('#modelId').val(result.modelId);
                        if(result.active){
                            $('input[name=active][value=true]').attr('checked', true);
                        }else{
                            $('input[name=active][value=false]').attr('checked', true);
                        }
                        $('#sequence').val(result.sequence);
                        if(result.parentMenu){
                            $('#parentMenuNo').val(result.parentMenu.no);
                        }
                    }
                );
        }

        function sp_save(){
            if(checkData()){
                var menu = {};
                menu.no = $('#no').val();
                menu.name = $('#name').val();
                menu.url = $('#url').val();
                menu.level = $('#level').val();
                menu.sequence = $('#sequence').val();
                menu.modelId=$('#modelId').val();
                menu.active = $('input:checked[name=active]').val();
                var parentMenu = {};
                parentMenu.no = $('#parentMenuNo').val();
                parentMenu.name = $('#parentMenuName').val();
                menu.parentMenu = parentMenu;

                $.post('/admin/system/addOrUpdateAdminMenu',
                        [
                            { name: 'jParam', value: JSON.stringify(menu) }
                        ],
                        function(result) {
                            if (result.code == 0){
                                Ext.Msg.alert("提示", "保存成功", function(result){
                                    if(result){
                                        window.location = "/admin/system/viewAdminMenus";
                                    }
                                });
                            } else {
                                Ext.Msg.alert("提示", "保存失败:"+result.message);
                            }
                        }
                    );
                return true;
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
            location.href = "/admin/system/viewAdminMenus";
        }
    </script>
</body>
</html>