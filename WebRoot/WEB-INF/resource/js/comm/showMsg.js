function showMsg(msg, title, timeout, showType) {
	if (!msg) {
		msg = "操作成功";
	}
	if (!timeout) {
		timeout = 2500;
	}
	if (!showType) {
		showType = 'fade';
	}
	$.messager.show({
		msg : msg,
		title : title,
		timeout : timeout,
		showType : showType
	});
}