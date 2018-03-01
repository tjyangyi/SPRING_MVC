$(function() {
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
				switch (XMLHttpRequest.status) {
				case 400:
					// 未授权异常
					alert("请求无效");
					break;
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
				// 打印responseText,方便排查问题
				console.log(XMLHttpRequest.responseText);
				// 错误方法增强处理
				fn.error(XMLHttpRequest, textStatus, errorThrown);
			},
			success : function(data, textStatus) {
				if (data.sessionInvalid) {
					// 跳转到登录页面，这里要加上top.为了防止在iframe界面中弹出了登录界面
					top.location.href = 'toLogin.do?message=' + data.message;
					return;
				}
				if (!textStatus) {
					showMsg(data.errorMsg, "操作失败,错误信息", 0, 200, 400);
				} else if (typeof (data.success) != 'undefined'
						&& !data.success) {
					showMsg(data.errorMsg, "操作失败,错误信息", 0, 200, 400);
				} else {
					fn.success(data, textStatus);// 成功回调方法增强处理
				}
			},
			beforeSend : function(XHR) {
				// 提交前回调方法，全局统一的等待提示框
				// 例如$('body').append("<div id='ajaxInfo'
				// style=''>正在加载,请稍后...</div>");
			},
			complete : function(XHR, TS) {
				// 请求完成后回调函数 (请求成功或失败之后均调用)。
				// 取消全局统一的等待提示框,例如//$("#ajaxInfo").remove();
			}
		});
		return _ajax(_opt);
	};
});
