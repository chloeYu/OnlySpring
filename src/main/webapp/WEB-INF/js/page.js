
$(function() {
	/*$("#page_box").hide();*/
	$("#pid").hide();
	
	for(var i=1; i<12; i++){
		var html="<div class='page_box' id='page_box_'"+i+"><img alt='' src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_OHGUdWw_d6uII-s3E1v3zVTVUwL06Ak6oVySI4EZDbVtrw1KMA'>"
				+"<br><div id='pagename'><a href='#pageLike'>pagename</a><br><b>pagecategory</b>"
				+"</div></div>";
		$("#page_box_Container").append(html);
	}
});
