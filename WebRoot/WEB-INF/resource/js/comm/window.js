var sizeObject = new Object();
$(function(){
	sizeObject.windowHeight = $(window).height(); 　              // 浏览器时下窗口可视区域高度
	sizeObject.documentHeight = $(document).height();　           // 浏览器时下窗口文档的高度
	sizeObject.documentBodyHeight = $(document.body).height();　　　　　　// 浏览器时下窗口文档body的高度
	sizeObject.documentBodyOuterHeight = $(document.body).outerHeight(true);　// 浏览器时下窗口文档body的总高度 包括border padding margin
	sizeObject.windowWidth = $(window).width();   // 浏览器时下窗口可视区域宽度
	sizeObject.documentWidth = $(document).width();  // 浏览器时下窗口文档对于象宽度
	sizeObject.documentBodyWidth = $(document.body).width();　　　　// 浏览器时下窗口文档body的高度
	sizeObject.documentBodyOuterWidht = $(document.body).outerWidth(true);　// 浏览器时下窗口文档body的总宽度 包括border padding
	console.log(sizeObject);
});