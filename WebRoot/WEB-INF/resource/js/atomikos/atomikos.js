function saveOracle() {
	$.post("saveOracle", {
	}, function(data, status) {
		console.log(data);
		console.log(status);
	});
}

function saveMysql() {
	$.post("saveMysql", {
	}, function(data, status) {
		console.log(data);
		console.log(status);
	});
}

function saveBoth() {
	$.post("saveBoth", {
	}, function(data, status) {
		console.log(data);
		console.log(status);
	});
}

function saveBothWithOracleException() {
	$.post("saveBothWithOracleException", {
	}, function(data, status) {
		console.log(data);
		console.log(status);
	});
}

function saveBothWithMysqlException() {
	$.post("saveBothWithMysqlException", {
	}, function(data, status) {
		console.log(data);
		console.log(status);
	});
}
