function callParentFunction() {
	parent.backgroudWindowFunction("对话框调用父窗口的方法参数1", "参数2");
}

function cancel() {
	parent.closeDialog();
}

function functionInDialog1(param1, param2) {
	console.log("functionInDialog1");
	console.log("param1 = " + param1);
	console.log("param2 = " + param2);
}

function closeDialog() {
	if (dialog) {
		dialog.close();
	}
}

var dialog;
function openInnerDialog(){
	dialog = jQuery.dialog({
		title : "第二级对话框",
		id : "innerJqueryDialog",
		width : 400 + 'px',
		height : 300 + 'px',
		top : 300 + 'px',
		zIndex: 3000,
		draggable: true,
		content : "url:" + "toJqueryDialogContentInnerDialog",
		close : function() {
			dialog = null;
		},
	});
}