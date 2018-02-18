function baseDao_save() {
	$.post("baseDaoSave", {
		name : "姓名",
		countNum : 24
	}, function(data, status) {
		console.log(data);
		console.log(status);
		showMsg("save成功");
		dg1_search();
		dg2_search();
	});
}

function baseDao_update() {
	var rows = $("#dg1").datagrid("getRows"); // 这段代码是获取当前页的所有行。
	if (rows == null || rows.length == 0) {
		showMsg("没有数据，请添加一条");
	}
	var row = rows[0];
	$.post("baseDaoUpdate", {
		id : row.id,
		name : "姓名7",
		countNum : row.countNum
	}, function(data, status) {
		console.log(data);
		console.log(status);
		showMsg("update");
		dg1_search();
		dg2_search();
	});
}

function baseDao_saveOrUpdate() {

}

function baseDao_saveOrUpdate() {

}

function dg1_search() {
	$('#dg1').datagrid('load', {
		startTime : $('#dg1_startTime').val(),
		endTime : $('#dg1_endTime').val(),
		name : $('#dg1_name').val().trim(),
		countNum : $('#dg1_countNum').val()
	});
}

function dg2_search() {
	$('#dg2').datagrid('load', {
		startTime : $('#dg2_startTime').val(),
		endTime : $('#dg2_endTime').val(),
		name : $('#dg2_name').val().trim(),
		countNum : $('#dg2_countNum').val()
	});
}