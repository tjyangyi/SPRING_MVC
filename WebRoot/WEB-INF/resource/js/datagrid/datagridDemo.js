function onSearchClick(){
	$('#dg').datagrid('load',{
		startTime: $('#startTime').val(),
		endTime: $('#endTime').val()
	});
}