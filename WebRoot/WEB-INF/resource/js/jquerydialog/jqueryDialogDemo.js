function back() {
	window.location.href = "toIndex.do"
}

function callDialogFunction() {
	if (typeof (jqueryDialog1) != "undefined") {
		jqueryDialog1.window.functionInDialog1("父窗口调用dialog方法参数1", "参数2");
	}
}

function backgroudWindowFunction(param1, param2) {
	console.log("backgroudWindowFunction");
	console.log("param1 = " + param1);
	console.log("param2 = " + param2);
}

function closeDialog() {
	if (dialog) {
		dialog.close();
	}
}

var dialog;
function openDialog() {
	dialog = jQuery.dialog({
		title : "对话框",
		id : "jqueryDialog1",
		width : 800 + 'px',
		height : 500 + 'px',
		top : 200 + 'px',
		draggable : true,
		content : "url:" + "toJqueryDialogContent",
		close : function() {
			dialog = null;
		},
	});
}