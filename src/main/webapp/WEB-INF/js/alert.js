var files = null;

$(function(){
	$('body').on('click', '.postPopup', function(){
		console.log("postPopup");
		$('#displayAlarmList').hide();
		$.post('checkReadNotification', "aid="+$(this).attr('data-aid'), function(data){
			if(data == 1){
				console.log("Read");
			}
		});
		$.post('getPostFiles', "pid="+$(this).attr('data-pid'), function(data){
			console.log("postPopup");
			console.log(data);
			files = data;
			
			var modal =document.getElementById("myModal");
			var type = 0; // 0 : 이미지 1 : 비디오
			console.log(files[0].url);
			if(isImage(files[0].url)){
				$('#popUpImage').show();
				var modalImg = document.getElementById("popUpImage");
				modal.style.display = "block";
				modalImg.src = '/only/img_timeline/'+files[0].url;
				$('#iframeYoutube').hide();
			}
			if(isVideo(files[0].url)){
				$('#iframeYoutube').show();
				$('#popUpImage').hide();
				var modalVideo = document.getElementById("iframeYoutube");
				modal.style.display = "block";
				modalVideo.src = '/only/img_timeline/'+files[0].url;
			}

			$('.prevIcon').attr('data-index', 0);
			$('.nextIcon').attr('data-index', 0);
			$('.prevIcon').hide();
			if(files.length-1 == 0){
				$('.nextIcon').hide();
			}
			$('.prevIcon').attr('data-type', 'alert');
			$('.nextIcon').attr('data-type', 'alert');
		});
	});
	
	
	$('body').on('click', '.prevIcon', function(){
		if($(this).attr('data-type')=='alert'){
			var index = parseInt($(this).attr('data-index')) - 1;
			
			if(isImage(files[index].url)){
				$('#popUpImage').show();
				$('#popUpImage').attr('src', '/only/img_timeline/'+files[index].url);
				$('#iframeYoutube').hide();
			}
			if(isVideo(files[index].url)){
				$('#iframeYoutube').show();
				$('#iframeYoutube').attr('src', '/only/img_timeline/'+files[index].url);
				$('#popUpImage').hide();
			}
			
			if(index == 0){
				$('.prevIcon').hide();
			}
			$('.nextIcon').show();
			
			$('.prevIcon').attr('data-index', index);
			$('.nextIcon').attr('data-index', index);
		}
	});
	
	$('body').on('click', '.nextIcon', function(){
		if($(this).attr('data-type')=='alert'){
			var index = parseInt($(this).attr('data-index')) + 1;
			if(isImage(files[index].url)){
				$('#popUpImage').show();
				$('#popUpImage').attr('src', '/only/img_timeline/'+files[index].url);
				$('#iframeYoutube').hide();
			}
			if(isVideo(files[index].url)){
				$('#iframeYoutube').show();
				$('#iframeYoutube').attr('src', '/only/img_timeline/'+files[index].url);
				$('#popUpImage').hide();
			}
			
			if(index == files.length-1){
				$('.nextIcon').hide();
			}
			$('.prevIcon').show();
			
			$('.prevIcon').attr('data-index', index);
			$('.nextIcon').attr('data-index', index);
		}
	});
});