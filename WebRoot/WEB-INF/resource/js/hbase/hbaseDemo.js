function scanWithStartAndStop() {
	// 一般使用简单易用的高层实现 $.get, $.post等
	// 大多数情况下你无需直接操作$.ajax函数，除非你需要操作不常用的选项，以获得更多的灵活性
	$.post("scanWithStartAndStop", {
		"startRow" : "49217_20161013000000",
		"stopRow" : "49217_20181013000000"
	}, function(data, status) {
		console.log(data);
		console.log(status);
	});
}