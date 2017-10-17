var scrollHandler = function(){
    myScroll = $(window).scrollTop();
}

var picLoad = 0;
var loadedImage = 0;
var updateType = 0;
$(function(){
	$('body').on('click', '.editCoverBtn', function(){
		if($(this).attr('data-type')=='cover'){
			updateType = 0; // cover이미지 수정
			$("#updateType").val("0");
			console.log("Cover " + $("#updateType").val());
		} else if($(this).attr('data-type')=='thumb'){
			updateType = 1; // thumb이지미 수정
			$("#updateType").val("1");
			console.log("Thumb " + $("#updateType").val())
		}
		console.log($(this).attr('data-type'));
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

$(document).ready(function() {
	$("#thumbProfileImg").on("change", handleProfileImgSelect);
});
function handleProfileImgSelect(e) {

	var file = e.target.files[0];
	console.log(file);

	var reader = new FileReader();

	reader.onload = function(e) {
			var image = new Image();
			var html ="<img src=\""
					+ e.target.result
					+ "\" data-file='"
					+ file.name
					+ "' style='max-width: 100%; max-height: 100%;'>"
					+ "<input type='submit' class='imageUpdateLabel' value='Upload'>";
			// 사진 크기 <a style>로 처리. css해주세욤
					$("#uploadedImage").html(html);
					// var img_html = "<img src='"+e.target.result+"'>";
	}
	reader.readAsDataURL(file);
}

function imagePreview(image){
	console.log("image clicked" + image);
	$('#uploadedImage').html('<img src="../img_timeline/'+image+'" style="max-width: 100%; max-height:100%;">');
	$('#aaaaa').append('<a href="/only/updateProfileImage?url='+image+'&type='+updateType+'" class="imageUpdateLabel">Update</a>');
}
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
						'<a style="display: inline-block" onclick="imagePreview(\''+value+'\')">'+
						'<img src="../img_timeline/'+value+'" width="150" height="150"></a></td>');
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

function getFriendList(){
	var urlParse = window.location.href.split('/');
	var owner = urlParse[urlParse.length-1];
	console.log(owner);
	$.post('/only/getFriendListByUserid', "userid="+owner, function(data){
		console.log(data);
		$('.blogTimelineCapsule').hide();
		$('.blogFriendList').html(data);
		$('.blogFriendList').show();
	});
}
