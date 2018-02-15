//操作列
function operationFormatter(val, row, index) {
	var r = '<button onclick="triggerJob({row.jobName},{row.jobGroup});">运行</button>'
			+ '<button onclick="edit({row.jobName},{row.jobGroup});">编辑</button>'
			+ '<button onclick="pauseJob({row.jobName},{row.jobGroup});">暂停</button>'
			+ '<button onclick="resumeJob({row.jobName},{row.jobGroup});">恢复</button>'
			+ '<button onclick="deleteJob({row.jobName},{row.jobGroup},{row.triggerName},{row.triggerGroupName});">删除</button>';
	r = r.replaceAll("{row.jobName}", "'" + row.jobName + "'");
	r = r.replaceAll("{row.jobGroup}", "'" + row.jobGroup + "'");
	r = r.replaceAll("{row.triggerName}", "'" + row.triggerName + "'");
	r = r
			.replaceAll("{row.triggerGroupName}", "'" + row.triggerGroupName
					+ "'");
	return r;
}

// 刷新datagrid
function refreshData() {
	$('#datagrid').datagrid('load');
}

function openAddJobDialog() {
	$("#addJobDialog")
			.dialog(
					{
						title : '新增任务',
						left: (sizeObject.windowWidth - 500)/2,
						top: (sizeObject.windowHeight - 500)/2,
						width : 500,
						height : 500,
						modal : true,
						content : "<iframe id='addJobIframe' scrolling='auto' frameborder='0' src='quartzToAddJob.do' style='width:100%; height:100%; display:block;'></iframe>",
						buttons : [ {
							text : '保存',
							iconCls:'icon-save',
							handler : function() {
								$("#addJobIframe")[0].contentWindow.test1();
							}
						}, {
							text : '关闭',
							iconCls:'icon-cancel',
						} ]

					});
	$("#addJobDialog").dialog("open"); // 打开dialog
}

function addJob() {
	console.log($('#addJobDialog'));
	$('#addJobDialog').test1();
}

// 跳转到新增任务界面
function quartzToAddJob() {
	window.location.href = "quartzToAddJob.do";
}

// 跳转到编辑任务界面
function edit(jobName, jobGroup) {
	window.location.href = "quartzToEdit.do?jobName=" + jobName + "&jobGroup="
			+ jobGroup;
}

// 运行任务
function triggerJob(jobName, jobGroup) {
	$.post("triggerJob.do", {
		jobName : jobName,
		jobGroup : jobGroup
	}, function(data, status) {
		if (status && data.success) {
			alert("运行成功");
			refreshData();
		} else {
			alert("运行失败,errorMsg=" + data.errorMsg);
		}
	});
}

// 暂停任务
function pauseJob(jobName, jobGroup) {
	$.post("quartzPauseJob.do", {
		jobName : jobName,
		jobGroup : jobGroup
	}, function(data, status) {
		if (status && data.success) {
			alert("暂停成功");
			refreshData();
		} else {
			alert("暂停失败,errorMsg=" + data.errorMsg);
		}
	});
}

// 恢复任务
function resumeJob(jobName, jobGroup) {
	$.post("quartzResumeJob.do", {
		jobName : jobName,
		jobGroup : jobGroup
	}, function(data, status) {
		if (status && data.success) {
			alert("恢复成功");
			refreshData();
		} else {
			alert("恢复失败,errorMsg=" + data.errorMsg);
		}
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
		if (status && data.success) {
			alert("删除成功");
			refreshData();
		} else {
			alert("删除失败,errorMsg=" + data.errorMsg);
		}
	});
}
