function back() {
	window.location.href = "toIndex.do"
}

function openDialog() {
	var dialog1 = jQuery.dialog({
		title : "对话框",
		id : "dialog1",
		width : 800 + 'px',
		height : 225 + 'px',
		top : 200 + 'px',
		close : function() {
			dialog1 = null;
		}
	});
}