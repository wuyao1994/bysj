<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="/resources/css/ext4.2.1/ext-theme-classic-all.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/askmobile-admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="manage-head">
        <table>
            <tr>
                <td>
                    <label for="keyWord" class="myLabel">关键字</label>
                    <input type="text" name="keyWord" id="keyWord" class="myInput" value="" />
                    <input type="button" class="myButton" value="搜索" onclick="searchUser();">
                </td>
            </tr>
        </table>
    </div>
    <div class="manage-body">
        <div id="AdminUser-grid"></div>
    </div>
    <script src="/resources/scripts/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="/resources/scripts/ext4.2.1/ext-all.js" type="text/javascript" ></script>
    <script src="/resources/scripts/admin/viewAdminUsers.js" type="text/javascript"></script>
</body>
</html>