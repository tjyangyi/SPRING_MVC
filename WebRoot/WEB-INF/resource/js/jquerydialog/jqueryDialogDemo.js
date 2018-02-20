var dialog1;

function back() {
	window.location.href = "toIndex.do"
}

function callDialogFunction() {
	if (dialog1 != null) {
		dialog1[0].contentWindow.functionInDialog("父窗口调用dialog函数", "参数2");
	}
}

function backgroudWindowFunction(param1, param2) {
	console.log("backgroudWindowFunction");
	console.log("param1 = " + param1);
	console.log("param2 = " + param2);
	if (dialog1) {
		dialog1.close();
	}
}

function openDialog() {
	dialog1 = jQuery.dialog({
		title : "对话框",
		id : "dialog1",
		width : 800 + 'px',
		height : 225 + 'px',
		top : 200 + 'px',
		content : "url:" + "toJqueryDialogContent",
		close : function() {
			dialog1 = null;
		},
	});
}