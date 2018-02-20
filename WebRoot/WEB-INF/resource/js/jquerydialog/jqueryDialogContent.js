function callParentFunction() {
	parent.parentWindowFunction("对话框调用父窗口的方法参数1", "参数2");
}

function cancel() {
	parent.closeDialog();
}

function functionInDialog(param1, param2) {
	console.log("functionInDialog");
	console.log("param1 = " + param1);
	console.log("param2 = " + param2);
}