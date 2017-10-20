// 글 내용 없을 때 작성버튼 비활성화
//$(function() {
//	if ($('.type_choice_textarea').val() == '') {
//		$('post_submit_btn').prop('disabled', true);
//		$('post_submit_btn').css('opacity', 0.5);
//	} else {
//		$('post_submit_btn').prop('disabled', false);
//		$('post_submit_btn').css('opacity', 1);
//	}
//});
// 글 내용 없을 때 작성버튼 비활성화 끝
var latitude  = 36.322473;
var longitude = 127.412501;
var a = false;

$(function() {
	$(".type_choice_textarea").bind("keydown", function(e) {
		var text = $(".type_choice_textarea").val();
		var positionOfhash = new Array();
		var positionOfspace = new Array();
		var indexOfhash = text.indexOf("#");
		var indexOfspace = text.indexOf(" ", indexOfhash);
		while (indexOfhash > -1) {
			if(indexOfspace==-1)
				indexOfspace=null;
			positionOfhash.push(indexOfhash);
			positionOfspace.push(indexOfspace);
			indexOfhash = text.indexOf("#", indexOfhash + 1);
			indexOfspace = text.indexOf(" ", indexOfhash);
		}
		if (positionOfhash == null || positionOfspace == null)
			return true;
		else {
			for (var i = 0; i < positionOfhash.length; i++)
			if (positionOfspace[i]==null) {
				console.log("hashTag not finished");
				console.log(text.slice(positionOfhash[i]));
			} else
				console.log(text.slice(positionOfhash[i], positionOfspace[i]));
		}
	});
	$(".friend_list").on("click", function(){
		if(a){
			$(".friendlistplace").addClass("disapper").removeClass("show");
		} else{
			$.get("/only/friendList", "member_id="+$("#member_id").val(), function(data){
				$("#friendload").html(data);
			});
			$(".friendlistplace").removeClass("disapper").addClass("show");
		}
		a=!a;
	});
	$('.post_submit_btn').prop('disabled',true);
	// textarea focus일 때 작성폼 열기
	$('.type_choice_textarea').on('focus', function() {
		$(this).css("height", "150px");
		$('.img_hide').removeClass('img_hidden');
	});
	// 내용 작성시 submit 활성화
	$('.type_choice_textarea').on('keyup',function(){
		if ($('.type_choice_textarea').val()==''){
			$('.post_submit_btn').prop('disabled',true);
			$('.post_submit_btn').css('opacity',0.5);
		} else {
			$('.post_submit_btn').prop('disabled', false);
			$('.post_submit_btn').css('opacity', 1);
		}
	})
	// blur시 비활성화
	$('.type_choice_textarea').on('blur', function() {
		if ($('.type_choice_textarea').val() == '') {
			$(this).css("height", "30px");
			$('.img_hide').addClass('img_hidden');
			$('.post_submit_btn').prop('disabled',true);
			$('.post_submit_btn').css('opacity',0.5);
			$("#pac-input").hide();
			$("#map").hide();
			$("#google_button").hide();
		}
	});
	//지도
	$('#google').on('click', function() {
		$("#pac-input").show();
		$("#map").show();
		$("#google_button").show();
		getLocation();
		setTimeout(initAutocomplete1, 500);
	});
	
	$('body').on('click', '.removeMapBtn', function(){
		$('#google_place_view').html('');
		$('#lat').val(0.0);
		$('#lng').val(0.0);
		$('#place').val('');
	});
	
	
	$('body').on('click', '.postInner3', function(e){
		var modal =document.getElementById("myModal");
		//Get the image and insert it inside the modal - use its "alt" text as a caption
		var img =  e.currentTarget;
		console.log(img.src);
		var type = 0; // 0 : 이미지 1 : 비디오
		if(isImage(e.currentTarget.src)){
			$('#popUpImage').show();
			var modalImg = document.getElementById("popUpImage");
			modal.style.display = "block";
			modalImg.src = e.currentTarget.src;
			$('#iframeYoutube').hide();
		}
		if(isVideo(e.currentTarget.src)){
			$('#iframeYoutube').show();
			$('#popUpImage').hide();
			var modalVideo = document.getElementById("iframeYoutube");
			modal.style.display = "block";
			modalVideo.src = e.currentTarget.src;
		}

		console.log($(this).attr('data-index'));
		console.log($(this).attr('data-total'));
		$('.prevIcon').attr('data-id', $(this).attr('id'));
		$('.prevIcon').attr('data-index', $(this).attr('data-index'));
		$('.prevIcon').attr('data-total', $(this).attr('data-total'));

		$('.nextIcon').attr('data-id', $(this).attr('id'));
		$('.nextIcon').attr('data-index', $(this).attr('data-index'));
		$('.nextIcon').attr('data-total', $(this).attr('data-total'));
		
		if($(this).attr('data-index') == 0){
			$('.prevIcon').hide();
		}
		if($(this).attr('data-index') == $(this).attr('data-total')-1){
			$('.next').hide();
		}
		//var captionText = document.getElementById("caption");
		//captionText.innerHTML = this.alt;
	});
	
	$('body').on('click', '.prevIcon', function(){
		var prefix = $(this).attr('data-id').split('-')[0];
		var index = parseInt($(this).attr('data-id').split('-')[1]) - 1;
		console.log($(this).attr('data-id'));
		console.log(prefix+index);
		
		if(isImage($('#'+prefix+'-'+index).attr('src'))){
			$('#popUpImage').show();
			$('#popUpImage').attr('src', $('#'+prefix+'-'+index).attr('src'));
			$('#iframeYoutube').hide();
		}
		if(isVideo($('#'+prefix+'-'+index).attr('src'))){
			$('#iframeYoutube').show();
			$('#iframeYoutube').attr('src', $('#'+prefix+'-'+index).attr('src'));
			$('#popUpImage').hide();
		}
		
		if(index == 0){
			$('.prevIcon').hide();
		}
		$('.nextIcon').show();
		
		$('.prevIcon').attr('data-id', prefix+'-'+index);
		$('.prevIcon').attr('data-index', index);
		$('.prevIcon').attr('data-total', $(this).attr('data-total'));

		$('.nextIcon').attr('data-id', prefix+'-'+index);
		$('.nextIcon').attr('data-index', index);
		$('.nextIcon').attr('data-total', $(this).attr('data-total'));
	});
	
	$('body').on('click', '.nextIcon', function(){
		var prefix = $(this).attr('data-id').split('-')[0];
		var index = parseInt($(this).attr('data-id').split('-')[1]) + 1;
		console.log($(this).attr('data-id'));
		console.log(prefix+index);
		if(isImage($('#'+prefix+'-'+index).attr('src'))){
			$('#popUpImage').show();
			$('#popUpImage').attr('src', $('#'+prefix+'-'+index).attr('src'));
			$('#iframeYoutube').hide();
		}
		if(isVideo($('#'+prefix+'-'+index).attr('src'))){
			$('#iframeYoutube').show();
			$('#iframeYoutube').attr('src', $('#'+prefix+'-'+index).attr('src'));
			$('#popUpImage').hide();
		}
		
		if(index == $(this).attr('data-total')-1){
			$('.nextIcon').hide();
		}
		$('.prevIcon').show();
		
		$('.prevIcon').attr('data-id', prefix+'-'+index);
		$('.prevIcon').attr('data-index', index);
		$('.prevIcon').attr('data-total', $(this).attr('data-total'));

		$('.nextIcon').attr('data-id', prefix+'-'+index);
		$('.nextIcon').attr('data-index', index);
		$('.nextIcon').attr('data-total', $(this).attr('data-total'));
	});
	
	
	$('body').on('click', '.nextIcon', function(){
		
	});
	
	//When the user clicks on <span> (x), close the modal
	$('body').on('click', '.popupClose', function(){
		var modal =document.getElementById("myModal");
		modal.style.display = "none";
	});
});
$(function(){	
	$('.dimClose').on('click', function(){
		console.log('click');
//		if($('.det').hasClass('show')){
		$(this).parents().children('.det').addClass('hide').removeClass('show');
//		}
	});
});
// infinite scroll 구현
/*
 * var page = 4; $(window) .scroll( function() { if
 * ($(window).scrollTop()+$(window).height() == $(document).height()) { for(var
 * z=0; z<6; z++){ $('.post_view_box') .append( '<li class="infinite_scroll">' +'<h3>' +
 * page++ +'번포스트</h3>' +'<hr> 테스트<br>테스트<br>테스트<br>테스트<br>테스트<br>테스트<br>테스트<br>' +'<hr>' +'<div
 * class="reactBtn">' +"<div class='heart'></div>" +'<div class="share_out"
 * onclick="openLayer("layerPop",200,18)"></div>' +'</div>' +'<div
 * class="commentForm">' +'<textarea rows="1" cols="1" name="text"
 * placeholder="댓글쓰기" class="comment_textarea"></textarea>' +'</div>' +'</li>' )} }
 * });
 */
// infinite scroll 끝
// Like 버튼 활성화
/*
 * $(function(){ $("ol").on('click','.heart', function(){
 * $(this).toggleClass('is_animating'); }); $("ol").on('animationend','.heart',
 * function(){ $(this).toggleClass('is_animating');
 * $(this).toggleClass('bg-position'); }); });
 */
// Like 버튼 활성화 끝

// 파일 타입 확인 메소드 시작 //
function getExtension(filename) {
    var parts = filename.split('.');
    return parts[parts.length - 1];
}

function isImage(filename) {
    var ext = getExtension(filename);
    switch (ext.toLowerCase()) {
    case 'jpg':
    case 'gif':
    case 'bmp':
    case 'png':
        //etc
        return true;
    }
    return false;
}

function isVideo(filename) {
    var ext = getExtension(filename);
    switch (ext.toLowerCase()) {
    case 'm4v':
    case 'avi':
    case 'mpg':
    case 'mp4':
    case 'wmv':
        // etc
        return true;
    }
    return false;
}
//파일 타입 확인 메소드 끝 //


// 이미지 미리보기
var sel_files = [];

$(document).ready(function() {
	$("#input_img").on("change", handleImgFileSelect);
});
function handleImgFileSelect(e) {
	/*
	 * sel_files = []; $("#preview").empty();
	 */
	console.log(e.target.files);
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);
	var index = 0;

	filesArr
			.forEach(function(f) {
				sel_files.push(f);
				var reader = new FileReader();
				reader.onload = function(e) {
					var image = new Image();
					var html = "<div class='imgPre'><a onclick=\"deleteImageAction("
							+ index
							+ ")\" id=\"img_id_"
							+ index
							+ "\">"
							+ "<img src=\""
							+ e.target.result
							+ "\" data-file='"
							+ f.name
							+ "' class='selProductFile' title='Click to remove'"
							+ "></a></div>";
					// 사진 크기 <a style>로 처리. css해주세욤
					$("#preview").append(html);
					index++;
					/* var img_html = "<img src='"+e.target.result+"'>"; */
				}
				reader.readAsDataURL(f);
			});
}
// 이미지 미리보기 지우기
function deleteImageAction(index) {
	console.log("index : " + index);
	sel_files.splice(index, 1);

	var img_id = "#img_id_" + index;
	$(img_id).remove();

	$("#input_imgs").val("54");

	console.log(sel_files);
}

// 이미지 상세(확장)
//function layer_popup(el){
//
//    var $el = $(el);        // 레이어의 id를 $el 변수에 저장
//    var isDim = $el.prev().hasClass('.postInner');   // dimmed 레이어를 감지하기 위한
//														// boolean 변수
//
//    isDim ? $('.imgDetail').fadeIn() : $el.fadeIn();
//
//    var $elWidth = ~~($el.outerWidth()),
//        $elHeight = ~~($el.outerHeight()),
//        docWidth = $(document).width(),
//        docHeight = $(document).height();
//
//    // 화면의 중앙에 레이어를 띄운다.
////    if ($elHeight < docHeight || $elWidth < docWidth) {
////        $el.css({
////            marginTop: -$elHeight /2,
////            marginLeft: -$elWidth/2
////        })
////    } else {
////        $el.css({top: 0, left: 0});
////    }
//
//    $el.find('a.btn-layerClose').click(function(){
//        isDim ? $('.imgDetail').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가
//															// 닫힌다.
//        return false;
//    });
//
//    $('.body').click(function(){
//        $('.imgDetail').fadeOut();
//        return false;
//    });
//}

/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */

//드롭다운
$(function() {
	function slideMenu() {
	    var activeState = $("#menu-container .menu-list").hasClass("active");
	    $("#menu-container .menu-list").animate(
	      {
	        right: activeState ? "0%" : "-100%"
	      },
	      500
	    );
	}
	$("#menu-toggle").click(function(event) {
		$(this).toggleClass('open');
	    event.stopPropagation();
	    $("#menu-container .menu-list").toggleClass("active");
	    slideMenu();

	    $("body").toggleClass("overflow-hidden");
	});
});
// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
	if(!event.target.matches('.instant')){
		if(a){
			$(".friendlistplace").addClass("disapper").removeClass("show");
			a=!a;
		}
	}
}
//지도
var google_map_place;

function initAutocomplete1() {
    var map = new google.maps.Map(document.getElementById('map'), {
    	center: {lat: latitude, lng: longitude},
      zoom: 16,
      mapTypeId: 'roadmap'
    });

    var input = document.getElementById('pac-input');
    var searchBox = new google.maps.places.SearchBox(input);
    /*map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);*/
    var google_button = document.getElementById('google_button');

    map.addListener('bounds_changed', function() {
      searchBox.setBounds(map.getBounds());
    });

    var markers = [];

    searchBox.addListener('places_changed', function() {
      var places = searchBox.getPlaces();
      
      if (places.length == 0) {
        return;
      }

      markers.forEach(function(marker) {
        marker.setMap(null);
      });
      markers = [];

      var bounds = new google.maps.LatLngBounds();
      places.forEach(function(place) {
        if (!place.geometry) {
          console.log("Returned place contains no geometry");
          return;
        }
        var icon = {
          url: place.icon,
          size: new google.maps.Size(71, 71),
          origin: new google.maps.Point(0, 0),
          anchor: new google.maps.Point(17, 34),
          scaledSize: new google.maps.Size(25, 25)
        };

        markers.push(new google.maps.Marker({
          map: map,
          icon: icon,
          title: place.name,
          position: place.geometry.location
        }));
        
        /*google_map_place = place.name;*/
        google_map_place = place;
        if (place.geometry.viewport) {
          bounds.union(place.geometry.viewport);
        } else {
          bounds.extend(place.geometry.location);
        }
      });
      map.fitBounds(bounds);
    });
    google.maps.event.addDomListener(google_button, 'click', function(){
    	$("#pac-input").hide();
    	$("#map").hide();
    	$("#google_button").hide();
    	$("#google_place_view").html(google_map_place.name+" 에서");
    	$("#google_place_view").append("<div class='removeMapBtn'></div>");
    	$("#place").val(google_map_place.name);
    	$("#lat").val(google_map_place.geometry.location.lat());
    	$("#lng").val(google_map_place.geometry.location.lng());
//+google_map_place.geometry.location + ", " + google_map_place.geometry.location.lat()
    });
 }

function getLocation(){
    {
        if (navigator.geolocation)
        {
            navigator.geolocation.getCurrentPosition(showPosition);
        }
        else{x.innerHTML="Geolocation is not supported by this browser.";}
    }

}

function showPosition(position) {
    latitude  = position.coords.latitude;
    longitude = position.coords.longitude;
/*    alert('latitude:' + latitude + 'longitude:' + longitude );*/
}

function friendupdate(status, uid1, uid2){
	$.post("/only/friendupdate/"+status+"/"+uid1+"/"+uid2, function(data){
		$.get("/only/friendList", "member_id="+$("#member_id").val(), function(data){
			$("#friendload").html(data);
		});
	});
}