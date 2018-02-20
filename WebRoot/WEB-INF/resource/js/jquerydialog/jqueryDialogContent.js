function save() {
	parent.backgroudWindowFunction("对话框调用父窗口的方法", "参数2");
	parent.dialog1.close();
}

function cancel() {
	parent.dialog1.close();
}

function functionInDialog(param1, param2) {
	console.log("functionInDialog");
	console.log("param1 = " + param1);
	console.log("param2 = " + param2);
}