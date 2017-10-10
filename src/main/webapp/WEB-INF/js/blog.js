var scrollHandler = function(){
    myScroll = $(window).scrollTop();
}

var picLoad = 0;
var loadedImage = 0;
$(function(){
	$('body').on('click', '.thumbImageUpdate', function(){
		if(picLoad==0){
			imageLoad(picLoad);
		}
	});
	
	$('body').on('click', '#updatePicRemoveBtn', function(){
		$('.updateContainer').css('height', $(window).height()/2);
		$(".updateWraper").css("display", "none");
		$("body").css("overflow", "scroll");
	});
	
});

function imageLoad(picLoad){
	picLoad++;
	if(picLoad>1){
		$('#picLoadMore-'+(picLoad-1)).remove();
	}
	$.post('/only/getImages', "userid="+userid+"&pageNum="+picLoad, function(data){
		console.log(data);
		if(data!=null){
			$('.photoDisplay').append('<table><tr>');
			$.each(data, function(key, value){
				loadedImage++;
				console.log(value);
				$('.photoDisplay').append('<td>'+
						'<a style="display: inline-block" href="/only/updateProfileImage?url='+value+'">'+
						'<img src="../img_timeline/'+value+' width="112" height="150"></a></td>');
			});
			$('.photoDisplay').append('</tr></table>');
			$.post('/only/getImageTotal', "userid="+userid, function(total){
				if(loadedImage < total){
					$('.photoDisplay').append('<a id="picLoadMore-'+picLoad+'" onclick="imageLoad('+picLoad+')"><span>Load More..</span></a>');
				}
			});
		}
	});
	console.log("thumbImageUpdate Clickeed");
	$(".updateWraper").css("display", "table");
	$("body").css("overflow", "hidden");
}