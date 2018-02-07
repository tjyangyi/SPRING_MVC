function add() {
	window.location.href = "quartzToAddJob.do";
}

function edit(jobName, jobGroup) {
	window.location.href = "quartz/toEdit.do?jobName=" + jobName + "&jobGroup="
			+ jobGroup;
}

// 暂停任务
function pauseJob(jobName, jobGroupName) {
	$.post(url + "/quartz/pauseJob.do", {
		"jobName" : jobName,
		"jobGroupName" : jobGroupName
	}, function(data) {
		if (data.status = 'success') {
			window.location.href = window.location.href;
		} else {
			alert("操作失败，请刷新重新！");
		}
	});
}

// 恢复任务
function resumeJob(jobName, jobGroupName) {
	$.post(url + "/quartz/resumeJob.do", {
		"jobName" : jobName,
		"jobGroupName" : jobGroupName
	}, function(data) {
		if (data.status = 'success') {
			window.location.href = window.location.href;
		} else {
			alert("操作失败，请刷新重新！");
		}
	});
}
// 删除
function deleteJob(jobName, jobGroupName, triggerName, triggerGroupName) {
	$.post(url + "/quartz/deleteJob.do", {
		"jobName" : jobName,
		"jobGroupName" : jobGroupName,
		"triggerName" : triggerName,
		"triggerGroupName" : triggerGroupName
	}, function(data) {
		if (data.status = 'success') {
			window.location.href = window.location.href;
		} else {
			alert("操作失败，请刷新重新！");
		}
	});
}

// 执行任务
function triggerJob(a, b) {
	var url = "triggerJob.do";
	var d = {
		jobName : a,
		jobGroupName : b
	};
	$.post(url, d, function(data) {
		if (data.status = 'ok') {
			window.location.href = window.location.href;
		}
	});
}