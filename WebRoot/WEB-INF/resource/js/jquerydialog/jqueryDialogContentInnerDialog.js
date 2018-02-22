function callParentDialogFunction() {
	jqueryDialog1.window.functionInDialog1("对话框调用父Dialog的方法参数1", "参数2");
}

function callBackgroundWindowFunction() {
	parent.backgroudWindowFunction("调用最底层窗口参数1", "参数2");
}

function cancel() {
	parent.closeDialog();
}