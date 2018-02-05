function onSendAjaxButtonClicked() {
	jQuery.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : "welcomeAjaxRequest.do",
		success : function(msg) {
			console.log(msg);
		}
	});
}