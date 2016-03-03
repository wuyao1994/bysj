var pageSize = 10;// 每页显示数量
var store = null;
Ext.require([ 'Ext.form.*', 'Ext.tip.QuickTipManager' ]);
Ext.onReady(function() {
    var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>', win;
    Ext.tip.QuickTipManager.init();
    Ext.define('roleModel', {
        extend : 'Ext.data.Model',
        fields : [ "no", "name", "active", "description", "createDate", "updateDate" ],
        idProperty : 'no'
    });
    store = Ext.create('Ext.data.Store', {
        pageSize : 20,
        model : 'roleModel',
        remoteSort : false,
        autoLoad : true,
        proxy : {
            type : 'ajax',
            url : '/admin/system/getAdminRoleList',
            actionMethods:{
                create: "POST", read: "POST", update: "POST", destroy: "POST"
            },
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
            text : '角色编号',
            dataIndex : 'no',
            minWidth:50,
            align : 'center'
        }, {
            text : '角色名称',
            dataIndex : 'name',
            minWidth:120,
            align : 'center'
        }, {
            text : '是否可用',
            dataIndex : 'active',
            minWidth: 50,
            align : 'center'
        }, {
            text : '描述',
            dataIndex : 'description',
            minWidth: 180,
            align : 'center'
        }, {
            text : '创建时间',
            dataIndex : 'createDate',
            minWidth: 90,
            align : 'center'
        }, {
            text : '最近更新时间',
            dataIndex : 'updateDate',
            minWidth: 90,
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
        } , "-", {
            text : '新增',
            iconCls : 'a_add',
            handler : addAdminRole
        }, "-", {
            text : '修改',
            iconCls : 'a_edit',
            handler : updateAdminRole
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
        renderTo : 'AdminRole-grid'
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
    function addAdminRole() {
        window.location.href = "/admin/system/editAdminRole";
    }
    function updateAdminRole() {
        var roleNo = null;
        var selection = grid.getView().getSelectionModel().getSelection()[0];
        if(selection){
            roleNo = selection.data.no;
            window.location.href = "/admin/system/editAdminRole?no=" + roleNo;
        }else{
            Ext.Msg.alert("提示", "请选择角色");
        }
    }
});

function searchRole() {
    store.load({
        params : {
            start : 0,
            limit : pageSize,
            keyWord : $("#keyWord").val()
        }
    });
}
