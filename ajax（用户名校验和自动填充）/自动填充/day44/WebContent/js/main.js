
$(function(){
	
	//1.轮播图手机滑动 start  
	var $allCarousel = $(".carousel");
	var startX ,endX ;
	
	
	$allCarousel.on("touchstart",function(event){
//		console.log("1 start" + event.originalEvent.touches[0].clientX);
		startX = event.originalEvent.touches[0].clientX;
	});
	$allCarousel.on("touchmove",function(event){
//		console.log("2 move" + event.originalEvent.touches[0].clientX);
		endX = event.originalEvent.touches[0].clientX;
	});
	$allCarousel.on("touchend",function(event){
		//触摸已经结束，获得不要touch坐标
//		console.log("3 end" + event.originalEvent.touches[0].clientX);
		//触发当前轮播图滑动
//		console.log( (endX - startX) > 0 ? "prev" : "next")
		$(this).carousel((endX - startX) > 0 ? "prev" : "next");
	});
	
	//1.轮播图手机滑动 end 
	
});
