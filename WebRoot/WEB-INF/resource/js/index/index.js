$(function() {
});

function ajaxWithoutAuth() {
	jQuery.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : "pageJumpWithoutAuth.do",
		success : function(msg) {
			console.log(msg);
		},
		error : function(msg) {
			console.log(msg);
		},
	});
}

function testHession() {
	jQuery.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : "testHession.do",
		success : function(msg) {
			console.log(msg);
		},
		error : function(msg) {
			console.log(msg);
		},
	});
}

function testNoHession() {
	jQuery.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : "testNoHession.do",
		success : function(msg) {
			console.log(msg);
		},
		error : function(msg) {
			console.log(msg);
		},
	});
}

function testHessionDatabase() {
	jQuery.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : "testHessionDatabase.do",
		success : function(msg) {
			console.log(msg);
		},
		error : function(msg) {
			console.log(msg);
		},
	});
}

function testKafkaProducerServer() {
	jQuery.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : "testKafkaProducerServer.do",
		success : function(msg) {
			console.log(msg);
		},
		error : function(msg) {
			console.log(msg);
		},
	});
}
