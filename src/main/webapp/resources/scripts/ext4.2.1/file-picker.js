var file = null;
var objPath = null;
var fileWindow = null;
var inputFile = null;
Ext.onReady(function () {	
	filePickerPanel = Ext.create('Ext.form.Panel', {
	    title: null,
	    width: 400,
	    bodyPadding: 10,
	    frame: true,
	    items: [{
	        xtype: 'filefield',
	        id: 'filePickerText',
	        fieldLabel: '选择文件',
	        labelWidth: 60,
	        msgTarget: 'side',
	        allowBlank: false,
	        anchor: '100%',
	        buttonText: '浏览…',
	        failure: function(form, action) {
	        	form.findFiled('filePickerText').setRawValue('');
	        }
	    }]
	});
	
	filePickerPanel2 = Ext.create('Ext.form.Panel', {
	    title: 'Upload a Photo',
	    width: 400,
	    bodyPadding: 10,
	    frame: true,
	    items: [{
	        xtype: 'filefield',
	        name: 'photo',
	        fieldLabel: 'Photo',
	        labelWidth: 50,
	        msgTarget: 'side',
	        allowBlank: false,
	        anchor: '100%',
	        buttonText: 'Select Photo...'
	    }]
	});
	
	Ext.create("Ext.ux.form.FileUploadField", {
        name: 'photo',
        fieldLabel: 'Photo',
        width: 400,
        labelWidth: 50,
        msgTarget: 'side',
        allowBlank: false,
        anchor: '100%',
	    render: Ext.get("equipPic2"),
        buttonText: 'Select Photo...'
	});
	
	fileWindow2 = new Ext.Window({
        title: '选择文件',
        width: 410,
        resizable: false,
        closable: true,
        closeAction:'hide',   //让窗体点击关闭按钮时隐藏窗口
        maximizable: false,
        minimizable: false,
        draggable:true,
        items:[filePickerPanel2],
        buttons:[{
            text:"取消",
            handler:hideWin
        },{
            text:"确定",
            handler:setResult
        }]
    });
	
    fileWindow = new Ext.Window({
        title: '选择文件',
        width: 410,
        resizable: false,
        closable: true,
        closeAction:'hide',   //让窗体点击关闭按钮时隐藏窗口
        maximizable: false,
        minimizable: false,
        draggable:true,
        items:[filePickerPanel],
        buttons:[{
            text:"取消",
            handler:hideWin
        },{
            text:"确定",
            handler:setResult
        }]
    });
    
    function hideWin(){
        fileWindow.hide(); //让窗体隐藏，取代close()造成二次打开失效
        Ext.getCmp("filePickerText").setRawValue(''); 
    }
    
    function setResult() {
    	fileWindow.hide();
    	document.getElementById(robj).setAttribute("value", Ext.getCmp("filePickerText").getRawValue());
    	document.getElementById(robj).object = Ext.getCmp("filePickerText");
        Ext.getCmp("filePickerText").setRawValue(''); 
    }
});
function PickFile(obj){
	var buttonId = obj.getAttribute("id");
    robj = buttonId.substring(6);
    fileWindow.show(this);
}

function PickFile2(obj){
	var buttonId = obj.getAttribute("id");
	objPath = buttonId.substring(6);
    robj = objPath.substring(4);
    //inputFile = document.getElementById(robj);
    fclick($("#"+robj));
    //fileWindow2.show(this);
}

function getPath(obj) {
	if (obj) {
		if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
			obj.select();

			return document.selection.createRange().text;
		}

		else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
			if (obj.files) {

				return obj.files.item(0).getAsDataURL();
			}
			return obj.value;
		}
		return obj.value;
	}
}

function fileUpLoad(obj) {
	var fieldId = "path"+obj.getAttribute("id");
	document.getElementById(fieldId).setAttribute("value", document.getElementById(obj.getAttribute("id")).value);
}