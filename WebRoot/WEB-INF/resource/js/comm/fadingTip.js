/**
 * 页面淡入淡出的提示 必须确保页面有fadingTipDiv
 * 
 * <link href="resource/css/gisAll.css" rel="stylesheet" type="text/css" />
 * <!--tip 提示--> <div id="fadingTipDiv" class="GisPrompt"
 * style="left:650px;top:160px;display:none;text-align:center;"> <div
 * class="GisPromptLine">没有符合条件的结果</div> </div>
 */
var timeOutFlag = null;
function fadingTip(tipText, time, color) {
	if (timeOutFlag) {
		window.clearTimeout(timeOutFlag);
		$("#fadingTipDiv").hide();
		$("#fadingTipDiv").find("div").html("");
	}
	if (!time)
		time = 1500;
	// 解决：当提示信息还未消失时，客户端tab切换，再切回来，有背影
	if ($("#fadingTipDiv") && $("#fadingTipDiv").attr("id")) {

	} else {
		var jHtml = '';
		jHtml += '<div id="fadingTipDiv" style="border:1px solid #222; position:absolute; width:200px; background:url(resource/images/newtoolbar/ts_bg.png) repeat; border-radius:5px; box-shadow: 0px 3px 3px rgba(0,0,0,0.5);overflow:hidden; z-index:9999; word-wrap:break-word;left:650px;top:160px;display:none;text-align:center;">';
		jHtml += "	<div style=\"border:1px solid #8e8e8e; padding:10px;  font:normal 14px/20px '微软雅黑','宋体'; border-radius:5px;/*圆角*/color:#ffffff;\">没有符合条件的结果</div>";
		jHtml += '</div>';
		$("body").append(jHtml);
	}
	var bodyW = $('body').width();
	var bodyH = $('body').height();
	var left = (bodyW - $('#fadingTipDiv').width()) / 2;
	var top = (bodyH - $('#fadingTipDiv').height()) / 2;
	$("#fadingTipDiv").css({
		'left' : left + 'px',
		'top' : top + 'px'
	});
	if (color) {
		$("#fadingTipDiv").css({
			'background' : 'none',
			"background-color" : "#0066cc"
		});
		$("#fadingTipDiv div").css('color', color);
	}
	$("#fadingTipDiv").find("div").html(tipText);
	$("#fadingTipDiv").show();
	timeOutFlag = setTimeout(function() {
		$("#fadingTipDiv").fadeOut("fast");
	}, time);
}

function hideFadingTipDiv() {
	$("#fadingTipDiv").remove();
}