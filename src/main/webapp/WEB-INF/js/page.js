
function pagestart() {
	select = $("#page_select option:selected").val();
	page_name = $("#page_name").val();
	$("#page_test").append(select+" "+page_name);
}

