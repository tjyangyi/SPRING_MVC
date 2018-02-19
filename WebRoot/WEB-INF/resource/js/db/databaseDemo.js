function baseDao_save() {
	$.post("baseDaoSave", {
		name : $('#get_name').val(),
		countNum : $('#get_countNum').val(),
		customTime : $('#get_customTime').val()
	}, function(data, status) {
		console.log(data);
		console.log(status);
		showMsg("save成功");
		dg1_search();
		dg2_search();
	});
}

function baseDao_update() {
	$.post("baseDaoUpdate", {
		id : $('#get_id').val(),
		name : $('#get_name').val(),
		countNum : $('#get_countNum').val(),
		customTime : $('#get_customTime').val()
	}, function(data, status) {
		console.log(data);
		console.log(status);
		showMsg("update成功");
		dg1_search();
		dg2_search();
	});
}

function baseDao_saveOrUpdate() {
	
	$.post("baseDaoSaveOrUpdate", {
		id : $('#get_id').val(),
		name : $('#get_name').val(),
		countNum : $('#get_countNum').val(),
		customTime : $('#get_customTime').val()
	}, function(data, status) {
		console.log(data);
		console.log(status);
		showMsg("saveOrUpdate成功");
		dg1_search();
		dg2_search();
	});
}


function baseDao_get() {
	var rows = $("#dg1").datagrid("getRows"); // 这段代码是获取当前页的所有行。
	if (rows == null || rows.length == 0) {
		showMsg("没有数据，请添加一条");
	}
	var row = rows[0];
	$('#get_id').textbox('setValue', row.id);
	$.post("baseDaoGet", {
		id : $('#get_id').val()
	}, function(data, status) {
		var demoTable = data.demoTable;
		console.log(demoTable);
		showMsg("get成功");
		$('#get_id').textbox('setValue', demoTable.id);
		$('#get_name').textbox('setValue', demoTable.name);
		$('#get_countNum').textbox('setValue', demoTable.countNum);
		$('#get_customTime').textbox('setValue',
				dateFormat2Second(demoTable.customTime));
		$('#get_createTime').textbox('setValue',
				dateFormat2Second(demoTable.createTime));
		$('#get_updateTime').textbox('setValue',
				dateFormat2Second(demoTable.updateTime));

	});
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

function dg3_search() {
	$('#dg3').datagrid('load', {
		name : $('#dg3_name').val().trim(),
	});
}

function dg4_search() {
	$('#dg4').datagrid('load', {
		name : $('#dg4_name').val().trim(),
	});
}

function dg5_search() {
	$('#dg5').datagrid('load', {
		name : $('#dg5_name').val().trim(),
	});
}
