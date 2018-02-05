$(function() {
	// ajaxError事件定位到document对象，文档内所有元素发生ajax请求异常，都将冒泡到document对象的ajaxError事件执行处理
	$(document).ajaxError(
	// 所有ajax请求异常的统一处理函数，处理
	function(event, xhr, options, exc) {
		if (xhr.status == 'undefined') {
			return;
		}
		switch (xhr.status) {
		case 403:
			// 未授权异常
			alert("您没有访问权限");
			break;
		case 404:
			alert("您访问的资源不存在");
			break;
		case 500:
			alert("内部服务器错误");
			break;
		}
	});
});