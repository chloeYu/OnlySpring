//page
$(function() {
	/*$("#pid").hide();*/
	
	var category = $("#category").val();
	
	if(category=1){
		$("#category_view").html("매장 또는 장소");
	}else if(category=2){
		$("#category_view").html("브랜드 또는 제품");
	}else if(category=3){
		$("#category_view").html("예술가, 밴드, 공인");
	}else {
		$("#category_view").html("기타");
	}
	
	
});