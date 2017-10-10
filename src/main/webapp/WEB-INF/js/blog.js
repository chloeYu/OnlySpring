var scrollHandler = function(){
    myScroll = $(window).scrollTop();
}

$(function(){
	$('body').on('click', '.thumbImageUpdate', function(){
		console.log("thumbImageUpdate Clickeed");
		$(".updateWraper").css("display", "table");
		$("body").css("overflow", "hidden");
		
	});
	
	$('body').on('click', '#updatePicRemoveBtn', function(){
		$(".updateWraper").css("display", "none");
		$("body").css("overflow", "scroll");
	});
	
});