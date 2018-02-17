window.onresize = function resize() {
	$('#datagrid').datagrid('resize', {
		height : $(window).height() - 130,
		width : $(window).width() - 40
	});
}

function back(){
	window.location.href = "toIndex.do"
}

$(function() {
	window.onresize();
});

// 操作列
function operationFormatter(val, row, index) {
	var r = '<a href="#" style="padding-left:3px;padding-right:3px" onclick="triggerJob({row.jobName},{row.jobGroup});">运行</a>'
			+ '|'
			+ '<a href="#" style="padding-left:3px;padding-right:3px" onclick="openEditJobDialog({row.jobName},{row.jobGroup});">编辑</a>'
			+ '|';
	if (row.triggerState != 'PAUSED') {
		r += '<a href="#" style="padding-left:3px;padding-right:3px" onclick="pauseJob({row.jobName},{row.jobGroup});">暂停</a>'
				+ '|';
	} else {
		r += '<a href="#" style="padding-left:3px;padding-right:3px" onclick="resumeJob({row.jobName},{row.jobGroup});">恢复</a>'
				+ '|';
	}
	r += '<a href="#" style="padding-left:3px;padding-right:3px" onclick="deleteJob({row.jobName},{row.jobGroup},{row.triggerName},{row.triggerGroupName});">删除</a>';
	r = r.replaceAll("{row.jobName}", "'" + row.jobName + "'");
	r = r.replaceAll("{row.jobGroup}", "'" + row.jobGroup + "'");
	r = r.replaceAll("{row.triggerName}", "'" + row.triggerName + "'");
	r = r
			.replaceAll("{row.triggerGroupName}", "'" + row.triggerGroupName
					+ "'");
	return r;
}

function refreshData_auto() {
	$('#datagrid').datagrid({
		loadMsg : 0
	});
	$('#datagrid').datagrid('load');
}

// 刷新datagrid
function refreshData_manual() {
	$('#datagrid').datagrid({
		loadMsg : '正在刷新,请稍等...'
	});
	$('#datagrid').datagrid('load');
	$.parser.parse($('#datagrid'));
}

function openAddJobDialog() {
	var addJobDialog = $("#addJobDialog");
	addJobDialog.saveCallback = function() {
		addJobDialog.dialog("close");
		showMsg('新增任务成功');
		refreshData_manual();
	}
	addJobDialog
			.dialog({
				title : '新增任务',
				left : (sizeObject.windowWidth - 500) / 2,
				top : (sizeObject.windowHeight - 500) / 2,
				width : 500,
				height : 500,
				modal : true,
				content : "<iframe id='addJobIframe' scrolling='auto' frameborder='0' src='quartzToAddJob.do' style='width:100%; height:100%; display:block;'></iframe>",
				buttons : [
						{
							text : '保存',
							iconCls : 'icon-save',
							handler : function() {
								$("#addJobIframe")[0].contentWindow
										.save(addJobDialog.saveCallback);
							}
						}, {
							text : '关闭',
							iconCls : 'icon-cancel',
							handler : function() {
								addJobDialog.dialog("close");
							}
						} ]

			});
	addJobDialog.dialog("open"); // 打开dialog
}

function openEditJobDialog(jobName, jobGroup) {
	var editJobDialog = $('#editJobDialog');
	editJobDialog.saveCallback = function() {
		editJobDialog.dialog("close");
		showMsg('编辑任务成功');
		refreshData_manual();
	}
	editJobDialog
			.dialog({
				title : '编辑任务',
				left : (sizeObject.windowWidth - 500) / 2,
				top : (sizeObject.windowHeight - 500) / 2,
				width : 500,
				height : 500,
				modal : true,
				content : "<iframe id='editJobIframe' scrolling='auto' frameborder='0' src='quartzToEdit.do?jobName="
						+ jobName
						+ "&jobGroup="
						+ jobGroup
						+ "' style='width:100%; height:100%; display:block;'></iframe>",
				buttons : [
						{
							text : '保存',
							iconCls : 'icon-save',
							handler : function() {
								$("#editJobIframe")[0].contentWindow
										.save(editJobDialog.saveCallback);
							}
						}, {
							text : '关闭',
							iconCls : 'icon-cancel',
							handler : function() {
								editJobDialog.dialog("close");
							}
						} ]

			});
	editJobDialog.dialog("open"); // 打开dialog
}

// 运行任务
function triggerJob(jobName, jobGroup) {
	$.post("triggerJob.do", {
		jobName : jobName,
		jobGroup : jobGroup
	}, function(data, status) {
		showMsg('运行成功');
		refreshData_manual();
	});
}

// 暂停任务
function pauseJob(jobName, jobGroup) {
	$.post("quartzPauseJob.do", {
		jobName : jobName,
		jobGroup : jobGroup
	}, function(data, status) {
		showMsg('暂停成功');
		refreshData_manual();
	});
}

// 恢复任务
function resumeJob(jobName, jobGroup) {
	$.post("quartzResumeJob.do", {
		jobName : jobName,
		jobGroup : jobGroup
	}, function(data, status) {
		showMsg('恢复成功');
		refreshData_manual();
	});
}

// 删除
function deleteJob(jobName, jobGroup, triggerName, triggerGroupName) {
	$.post("quartzDeleteJob.do", {
		jobName : jobName,
		jobGroup : jobGroup,
		triggerName : triggerName,
		triggerGroupName : triggerGroupName
	}, function(data, status) {
		showMsg('删除成功');
		refreshData_manual();
	});
}
