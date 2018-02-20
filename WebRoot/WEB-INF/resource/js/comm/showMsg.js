function showMsg(msg, title, timeout, height, width, showType) {
	if (!msg) {
		msg = "操作成功";
	}
	if (!timeout) {
		timeout = 2500;
	}
	if (!showType) {
		showType = 'fade';
	}
	if (!height) {
		height = 150;
	}
	if (!width) {
		width = 250;
	}
	$.messager.show({
		msg : msg,
		title : title,
		timeout : timeout,
		showType : showType,
		height : height,
		width : width
	});
}