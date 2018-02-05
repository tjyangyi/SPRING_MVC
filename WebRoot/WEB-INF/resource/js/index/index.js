$(function() {
	console.log("index.js $(function())");
});

function onSendAjaxButtonClicked() {
	jQuery.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : "welcomeAjaxRequest.do",
		success : function(msg) {
			console.log(msg);
		},
		error : function(msg) {
			console.log(msg);
		},
	});
}