var pageSize = 10;
var store = null;
Ext.require([ 'Ext.form.*', 'Ext.tip.QuickTipManager' ]);
Ext.onReady(function() {
    var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>', win;
    Ext.tip.QuickTipManager.init();
    Ext.define('memModel', {
        extend : 'Ext.data.Model',
        fields : [ "userId", "userName", "userPass","email", "active", "createDate", "updateDate" ],
        idProperty : 'pkey'
    });
    store = Ext.create('Ext.data.Store', {
        pageSize : 20,
        model : 'memModel',
        remoteSort : false,
        autoLoad : true,
        proxy : {
            type : 'ajax',
            actionMethods:{
                create: "POST", read: "POST", update: "POST", destroy: "POST"
            },
            url : '/admin/system/getAdminUserList',
            reader : {
                type : 'json',
                root : 'rows',
                totalProperty : 'total'
            }
        }
    });

    var grid = Ext.create('Ext.grid.Panel', {
        store : store,
        multiSelect : false,
        height : 500,
        layout : 'fit',
        columns : [ {
            text : '编号',
            dataIndex : 'userId',
            minWidth: 90,
            align : 'center'
        }, {
            text : '账号',
            dataIndex : 'userName',
            minWidth: 120,
            align : 'center'
        }, {
            text : '密码(加密)',
            dataIndex : 'userPass',
            minWidth: 300,
            align : 'left'
        }, {
            text : '邮箱',
            dataIndex : 'email',
            minWidth: 180,
            align : 'left'
        }, {
            text : '可用',
            dataIndex : 'active',
            minWidth: 90,
            align : 'center'
        }, {
            text : '创建日期',
            dataIndex : 'createDate',
            minWidth: 150,
            align : 'center'
        }, {
            text : '更新日期（最近）',
            dataIndex : 'updateDate',
            minWidth: 150,
            align : 'center'
        } ],
        listeners : {
            'itemclick' : function(view, record, item, index, e) {
                // Ext.MessageBox.alert("标题", record.data.equipNo);
            }
        },
        tbar : [ {
            text : '刷新',
            iconCls : 'a_refresh',
            handler : refreshPage
        },"-", {
            text : '新增',
            iconCls : 'a_add',
            handler : addAdmintUser
        }, "-", {
            text : '修改',
            iconCls : 'a_edit',
            handler : updateAdminUser
        } ],
        bbar : [ {
            xtype : 'pagingtoolbar',
            store : store,
            displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
            emptyMsg : "没有数据",
            beforePageText : "当前页",
            afterPageText : "共{0}页",
            displayInfo : true
        } ],
        renderTo : 'AdminUser-grid'
    });
    store.on('beforeload', function(store, options) {
        var searchParams = {
            keyWord : $("#keyWord").val()
        };
        Ext.apply(store.proxy.extraParams, searchParams);
    });

    function refreshPage() {
        store.loadPage(1);
    }
    function addAdmintUser() {
        window.location.href = "/admin/system/editAdminUser";
    }
    function updateAdminUser() {
        var userId = null;
        var selection = grid.getView().getSelectionModel().getSelection()[0];
        if(selection != undefined){
            userId = selection.data.userId;
            window.location.href = "/admin/system/editAdminUser?userId=" + userId;
        }else{
            Ext.MessageBox.alert("提示", "请选用戶数据！");
        }
    }
});
function searchUser() {
    store.load({
        params : {
            start : 0,
            limit : pageSize,
            keyWord : $("#keyWord").val()
        }
    });
}
