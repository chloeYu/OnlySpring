// textarea focus일 때 작성폼 열기
$(function() {
	$('.type_choice_textarea').on('focus', function() {
		$(this).css("height", "150px");
		$('.img_hide').removeClass('img_hidden');
	});
	$('.type_choice_textarea').on('blur', function() {
		if ($('textarea[name="text"]').val() == '') {
			$(this).css("height", "30px");
			$('.img_hide').addClass('img_hidden');
		}
	});
	
	// 지도
	$('#google').on('click', function() {
		$("#pac-input").show();
		$("#map").show();
		$("#google_button").show();
	});
});
// textarea focus일 때 작성폼 열기 끝
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
// 글 내용 없을 때 작성버튼 비활성화
$(function() {
	if ($('.type_choice_textarea').val() == '') {
		$('post_submit_btn').prop('disabled', true);
		$('post_submit_btn').css('opacity', 0.5);
	} else {
		$('post_submit_btn').prop('disabled', false);
		$('post_submit_btn').css('opacity', 1);
	}
});
// 글 내용 없을 때 작성버튼 비활성화 끝


// 이미지 미리보기
var sel_files = [];

$(document).ready(function() {
	$("#input_img").on("change", handleImgFileSelect);
});
function handleImgFileSelect(e) {
	/*
	 * sel_files = []; $("#preview").empty();
	 */

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
function layer_popup(el){

    var $el = $(el);        // 레이어의 id를 $el 변수에 저장
    var isDim = $el.prev().hasClass('.postInner');   // dimmed 레이어를 감지하기 위한
														// boolean 변수

    isDim ? $('.imgDetail').fadeIn() : $el.fadeIn();

    var $elWidth = ~~($el.outerWidth()),
        $elHeight = ~~($el.outerHeight()),
        docWidth = $(document).width(),
        docHeight = $(document).height();

    // 화면의 중앙에 레이어를 띄운다.
    if ($elHeight < docHeight || $elWidth < docWidth) {
        $el.css({
            marginTop: -$elHeight /2,
            marginLeft: -$elWidth/2
        })
    } else {
        $el.css({top: 0, left: 0});
    }

    $el.find('a.btn-layerClose').click(function(){
        isDim ? $('.imgDetail').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가
															// 닫힌다.
        return false;
    });

    $('.body').click(function(){
        $('.imgDetail').fadeOut();
        return false;
    });

}


// 지도
var google_map_place;

function initAutocomplete1() {
    var map = new google.maps.Map(document.getElementById('map'), {
    	center: {lat: 36.322473, lng: 127.412501},
      zoom: 18,
      mapTypeId: 'roadmap'
    });

    var input = document.getElementById('pac-input');
    var searchBox = new google.maps.places.SearchBox(input);
    /* map.controls[google.maps.ControlPosition.TOP_LEFT].push(input); */
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
        
        google_map_place = place.name;

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
    	  $("#google_place_view").append(google_map_place+" 에서");
      });
}
