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

	// Read posts
	var sendData = "userid=" + userid + "&pageNum=1";
	console.log(sendData);
	$.post('loadPost', sendData, function(data) {
		if (data == null) {
			$("#postList").html("No Post");
		} else {
			var postView;
			for (var i = 0; i < data.length; i++) {
				postView = buildPost(data[i]);
				$("#postList").append(postView);
			}

		}
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
// DimLayer
function dEI(elementID) {
	return document.getElementById(elementID);
}
// 레이어 팝업 열기
function openLayer(IdName, tpos, lpos) {
	var pop = dEI(IdName);
	pop.style.top = tpos + "px";
	pop.style.left = lpos + "%";
	pop.style.display = "block";

	var wrap = dEI("wrapper");
	var reservation = document.createElement("div");
	reservation.setAttribute("id", "deemed");
	wrap.appendChild(reservation);
}
// 레이어 팝업 닫기
function closeLayer(IdName) {
	var pop = dEI(IdName);
	pop.style.display = "none";
	var clearEl = parent.dEI("deemed");
	var momEl = parent.dEI("wrapper");
	momEl.removeChild(clearEl);
}
// DimLayer 끝
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

// Post Layout Build
function buildPost(postData) {
	console.log(postData);
	var heart = "<div class='heart' id='heart-" + postData.userid + "'></div>";
	var shareOut = "<div class='share_out' onclick='openLayer('layerPop',200,18)'></div>";
	var comment = "<form action='commentWrite'>"
			+ "<div class='commentForm'><input type='hidden' value='"
			+ userid
			+ "' name='userid'>"
			+ "<textarea row='1' cols='1' name='commentText' placeholder='Comments' class='comment_textarea'></textarea>"
			+ "<button class='commentBtn'>입력</button>"
			+ "<input type='hidden' value='" + postData.pid
			+ "' name='commentPid'>" + "</div>"
			+ "<div class='postLayoutClear'></div>" + "</form>";

	var postView = "<li class='infinite_scroll'><div class='postUid'><span>" + postData.userid + "</span></div><hr>";
	
	 for (var i = 0; i < postData.files.length; i++) {
		 if(postData.files.length == 1){ // 이미지 1개
			postView = postView + "<div class='postImg1'><img class='postInner1' src='img_timeline/" + postData.files[i].url + "'></div>"        
		 } else if(postData.files.length == 4){ // 이미지 4개
			postView = postView + "<div class='postImg2'><img class='postInner2' src='img_timeline/" + postData.files[i].url + "'></div>"
		 } else if (postData.files.length > 4){ // 이미지 4개 이상
			postView = postView + "<div class='postImg3'><img class='postInner3' src='img_timeline/" + postData.files[i].url + "'></div>"        
			$('.infinite_scroll .postImg3:nth-child(6)').append('<span>+MORE</span>');
		 }
	 }
	 $('.infinite_scroll .postImg3:nth-child(6)').on('click',function(){
		 var detailContainer = '<div class="det"></div>';
		 var appendDetail = '<div class="imgDetail" style="position:fixed; z-index:100; top:0; left:0; width:100%; height:100%;">'
			+'<div class="dimBackground" style="position:absolute; background-color:#000; opacity:0.5; width:100%; height:100%; top:0; left:0;">'
			+'</div>'
			+'<div class="detailDim" style="position:absolute; top:50%; left:20%; width:38%; height:auto; background-color:#FFF; opacity:1; z-index:10;">'
				+'<div class="postImg4">'
					+'<img class="postInner4" src="img_timeline/">'
				+'</div>'
			+'</div>'
		+'</div>';
		 
		 var imgPr = $(this);
		 $(this).parent().append(detailContainer);
		 $('.det').append(appendDetail);
		 layer_popup(imgPr);
	 });
	if (postData.text != null) {
		postView = postView + "<h3>" + postData.text + "</h3>"
	}
	postView = postView + "<div class='reactBtn'>" + heart + shareOut
			+ "</div>";
	postView = postView + comment;
	postView = postView + "</li>";

	return postView;
}

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

    var $el = $(el);        //레이어의 id를 $el 변수에 저장
    var isDim = $el.prev().hasClass('.postInner');   //dimmed 레이어를 감지하기 위한 boolean 변수

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
        isDim ? $('.imgDetail').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
        return false;
    });

    $('.body').click(function(){
        $('.imgDetail').fadeOut();
        return false;
    });

}