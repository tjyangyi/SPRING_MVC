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

(function($) {
	// 首先备份下jquery的ajax方法
	var _ajax = $.ajax;

	// 重写jquery的ajax方法
	$.ajax = function(opt) {
		// 备份opt中error和success方法
		var fn = {
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			},
			success : function(data, textStatus) {
			}
		}
		if (opt.error) {
			fn.error = opt.error;
		}
		if (opt.success) {
			fn.success = opt.success;
		}

		// 扩展增强处理
		var _opt = $.extend(opt, {
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				// 错误方法增强处理
				fn.error(XMLHttpRequest, textStatus, errorThrown);
			},
			success : function(data, textStatus) {
				if(textStatus && data.success){
					alert("操作成功");
					// 成功回调方法增强处理
					fn.success(data, textStatus);
				}else{
					alert("操作失败");
				}
				
			},
			beforeSend : function(XHR) {
				// 提交前回调方法
				$('body').append(
						"<div id='ajaxInfo' style=''>正在加载,请稍后...</div>");
			},
			complete : function(XHR, TS) {
				// 请求完成后回调函数 (请求成功或失败之后均调用)。
				$("#ajaxInfo").remove();
				;
			}
		});
		return _ajax(_opt);
	};
})(jQuery);