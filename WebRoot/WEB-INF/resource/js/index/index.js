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