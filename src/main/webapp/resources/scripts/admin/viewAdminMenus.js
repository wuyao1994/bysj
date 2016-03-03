var pageSize = 10;// 每页显示数量
var store = null;
Ext.require([ 'Ext.form.*', 'Ext.tip.QuickTipManager' ]);
Ext.onReady(function() {
    var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>', win;
    Ext.tip.QuickTipManager.init();
    Ext.define('menuModel', {
        extend : 'Ext.data.Model',
        fields : [ "no", "name", "url", "level", "sequence", "active", "parentMenu.no" ,"createDate", "updateDate" ],
        idProperty : 'pkey'
    });
    store = Ext.create('Ext.data.Store', {
        pageSize : 20,
        model : 'menuModel',
        remoteSort : false,
        autoLoad : true,
        proxy : {
            type : 'ajax',
            url : '/admin/system/getAdminMenuList',
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
            text : '菜单编号',
            dataIndex : 'no',
            minWidth:50,
            align : 'center'
        }, {
            text : '菜单名称',
            dataIndex : 'name',
            minWidth:120,
            align : 'center',
        }, {
            text : '菜单地址',
            dataIndex : 'url',
            minWidth: 250,
            align : 'left'
        }, {
            text : '等级',
            dataIndex : 'level',
            minWidth: 50,
            align : 'center'
        }, {
            text : '排序',
            dataIndex : 'sequence',
            minWidth: 50,
            align : 'center'
        }, {
            text : '父级菜单编号',
            dataIndex : 'parentMenu.no',
            minWidth: 50,
            align : 'center'
        }, {
            text : ' 创建日期',
            dataIndex : 'createDate',
            minWidth: 90,
            align : 'center'
        }, {
            text : '最近修改日期',
            dataIndex : 'updateDate',
            minWidth: 90,
            align : 'center'
        }],
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
            handler : addAdminMenu
        }, "-", {
            text : '修改',
            iconCls : 'a_edit',
            handler : updateAdminMenu
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
        renderTo : 'AdminMenu-grid'
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
    function addAdminMenu() {
        window.location.href = "/admin/system/editAdminMenu";
    }
    function updateAdminMenu() {
        var menuNo = null;
        var selection = grid.getView().getSelectionModel().getSelection()[0];
        if(selection){
            menuNo = selection.data.no;
            window.location.href = "/admin/system/editAdminMenu?no=" + menuNo;
        }else{
            Ext.Msg.alert("提示", "请选择菜单项");
        }
    }
});

function searchMenu() {
    alert(pageSize);
    store.load({
        params : {
            start : 0,
            limit : pageSize,
            keyWord : $("#keyWord").val()
        }
    });
}
