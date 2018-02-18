function onSearchClick() {
	$('#dg').datagrid('load', {
		startTime : $('#startTime').val(),
		endTime : $('#endTime').val(),
		name : $('#name').val().trim(),
		count : $('#count').val()
	});
}