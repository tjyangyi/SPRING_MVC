$(function() {
	if (window.parent != window) {//iframe中session超时,跳转打开窗口还是嵌入在iframe中问题解决
		window.parent.location.reload(true);
	}
});

function onSendAjaxButtonClicked() {
	jQuery.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : "welcomeAjaxRequest.do",
		success : function(msg) {
			console.log(msg);
		}
	});
}