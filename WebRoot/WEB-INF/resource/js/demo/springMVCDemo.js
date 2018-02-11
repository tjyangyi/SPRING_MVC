function springMVCReturnJSON() {
	// 一般使用简单易用的高层实现 $.get, $.post等
	// 大多数情况下你无需直接操作$.ajax函数，除非你需要操作不常用的选项，以获得更多的灵活性
	$.post("springMVCReturnJSON", {
		"param1" : "param1的值",
		"param2" : "param2的值"
	}, function(data, status) {
		console.log(data);
		console.log(status);
	});
}

function springMVCRequestParam1() {
	$.post("springMVCRequestParam1", {
		"param1" : "param1的值",
		//"param2" : "param2的值"
	}, function(data, status) {
		console.log(data);
		console.log(status);
	});
}

function springMVCRequestParam2() {
	$.post("springMVCRequestParam2", {
		"param1" : "param1的值",
		"param2" : "param2的值"
	}, function(data, status) {
		console.log(data);
		console.log(status);
	});
}

function springMVCRequestParam3() {
	$.post("springMVCRequestParam3", {
		"param1" : "param1的值",
		"param2" : "param2的值"
	}, function(data, status) {
		console.log(data);
		console.log(status);
	});
}

function exceptionForAJAX() {
	$.post("exceptionForAJAX", {}, function(data, status) {
		console.log(data);
		console.log(status);
	});
}