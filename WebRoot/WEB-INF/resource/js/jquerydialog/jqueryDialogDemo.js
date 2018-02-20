function back() {
	window.location.href = "toIndex.do"
}

function callDialogFunction() {
	if (typeof (jqueryDialog1) != "undefined") {
		jqueryDialog1.window.functionInDialog("父窗口调用dialog方法参数1", "参数2");
	}
}

function parentWindowFunction(param1, param2) {
	console.log("parentWindowFunction");
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
		height : 225 + 'px',
		top : 200 + 'px',
		content : "url:" + "toJqueryDialogContent",
		close : function() {
			dialog = null;
		},
	});
}