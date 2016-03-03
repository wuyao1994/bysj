var pageSize = 20;// 每页显示数量
var grantStore = null;
function loadRoleGrants(no) {
    Ext.tip.QuickTipManager.init();
    Ext.define('grantMode', {
        extend : 'Ext.data.Model',
        fields : [ "adminMenu.no", "adminMenu.name", "adminMenu.url", "view", "create", "update", "delete" ],
        idProperty : 'no'
    });
    grantStore = Ext.create('Ext.data.Store', {
        model : 'grantMode',
        remoteSort : false,
        autoLoad : true,
        proxy : {
            type : 'ajax',
            url : '/admin/system/getAdminGrantsByRole',
            actionMethods:{
                create: "POST", read: "POST", update: "POST", destroy: "POST"
            },
            reader : {
                type : 'json',
            },
            extraParams:{
                roleNo: no
            }
        }
    });

    var grid = new Ext.grid.Panel ({
        plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
            clicksToMoveEditor: 1,
            autoCancel: true
        })],
        store : grantStore,
        autoHeight: true,
        layout : 'fit',
        columns : [ {
            text : '菜单编号',
            dataIndex : 'adminMenu.no',
            minWidth: 50,
            align : 'center'
        }, {
            text : '菜单名称',
            dataIndex : 'adminMenu.name',
            minWidth:90,
            align : 'center'
        }, {
            text : '链接',
            dataIndex : 'adminMenu.url',
            minWidth: 220,
            align : 'left',
        }, {
            text : '查看',
            dataIndex : 'view',
            minWidth: 50,
            align : 'center',
            renderer: function(value){
                if(value)
                    return "<font color='green'>是</font>";
                else
                    return "<font color='red'>否</font>";
            },
            editor : Ext.create('Ext.form.field.ComboBox', {
                displayField : 'text',
                editable : false,
                hiddenName : 'type',
                labelStyle : 'text-align:right',
                name : 'type',
                repeatTriggerClick : true,
                store : createTrueFalseStore(),
                triggerAction : 'all',
                valueField : 'value',
                xtype : 'combo'
            })
        }, {
            text : '增加',
            dataIndex : 'create',
            minWidth: 50,
            align : 'center',
            renderer: function(value){
                if(value)
                    return "<font color='green'>是</font>";
                else
                    return "<font color='red'>否</font>";
            },
            editor : Ext.create('Ext.form.field.ComboBox', {
                displayField : 'text',
                editable : false,
                hiddenName : 'type',
                labelStyle : 'text-align:right',
                name : 'type',
                repeatTriggerClick : true,
                store : createTrueFalseStore(),
                triggerAction : 'all',
                valueField : 'value',
                xtype : 'combo'
            })
        }, {
            text : '修改',
            dataIndex : 'update',
            minWidth: 50,
            align : 'center',
            renderer: function(value){
                if(value)
                    return "<font color='green'>是</font>";
                else
                    return "<font color='red'>否</font>";
            },
            editor : Ext.create('Ext.form.field.ComboBox', {
                displayField : 'text',
                editable : false,
                hiddenName : 'type',
                labelStyle : 'text-align:right',
                name : 'type',
                repeatTriggerClick : true,
                store : createTrueFalseStore(),
                triggerAction : 'all',
                valueField : 'value',
                xtype : 'combo'
            })
        }, {
            text : '删除',
            dataIndex : 'delete',
            minWidth: 50,
            align : 'center',
            renderer: function(value){
                if(value)
                    return "<font color='green'>是</font>";
                else
                    return "<font color='red'>否</font>";
            },
            editor : Ext.create('Ext.form.field.ComboBox', {
                displayField : 'text',
                editable : false,
                hiddenName : 'type',
                labelStyle : 'text-align:right',
                name : 'type',
                repeatTriggerClick : true,
                store : createTrueFalseStore(),
                triggerAction : 'all',
                valueField : 'value',
                xtype : 'combo'
            })
        }],
        tbar : [ {
            text : '刷新',
            iconCls : 'a_refresh',
            handler : refreshPage
        }],
        renderTo : 'AdminGrant-grid'
    });
    function refreshPage() {
        store.loadPage(1);
    }
    function createTrueFalseStore() {
        return Ext.create('Ext.data.Store', {
            autoDestroy: true,
            fields:['value','text'],
            data: [{'value': true ,text:'是'},{'value': false ,text:'否'}]
        });
    }
};
