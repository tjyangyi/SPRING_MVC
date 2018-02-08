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

function add() {
	window.location.href = "quartzToAddJob.do";
}

function edit(jobName, jobGroup) {
	window.location.href = "quartzToEdit.do?jobName=" + jobName + "&jobGroup="
			+ jobGroup;
}

// 暂停任务
function pauseJob(jobName, jobGroupName) {
	$.post("quartzPauseJob.do", {
		"jobName" : jobName,
		"jobGroupName" : jobGroupName
	}, function(data) {
		if (data.status = 'success') {
			window.location.href = window.location.href;
			alert("暂停成功！");
		} else {
			alert("暂停失败！");
		}
	});
}

// 恢复任务
function resumeJob(jobName, jobGroupName) {
	$.post("quartzResumeJob.do", {
		"jobName" : jobName,
		"jobGroupName" : jobGroupName
	}, function(data) {
		if (data.status = 'success') {
			window.location.href = window.location.href;
			alert("恢复成功！");
		} else {
			alert("恢复失败！");
		}
	});
}
// 删除
function deleteJob(jobName, jobGroupName, triggerName, triggerGroupName) {
	$.post("quartzDeleteJob.do", {
		"jobName" : jobName,
		"jobGroupName" : jobGroupName,
		"triggerName" : triggerName,
		"triggerGroupName" : triggerGroupName
	}, function(data) {
		if (data.status = 'success') {
			window.location.href = window.location.href;
			alert("删除成功！");
		} else {
			alert("删除失败！");
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