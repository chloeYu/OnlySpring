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
	var sendData = "userid="+ userid +"&pageNum=1";
	console.log(sendData);
	$.post('loadPost', sendData, function(data) {
		if(data == null){
			 $("#postList").html("No Post");
		} else{
			var postView;
			for (var i = 0; i < data.length; i++) {
				$.post('getEachPost', "pid="+data[i].pid, function(postData){
					//results[i] = "<h3>"+postData.userid+"</h3><hr>";
					//results[i] = results[i] + "<h3>" + postData.text + "</h3><br>";
					//console.log(results[i]);
					postView = buildPost(postData);
					console.log(postView);
					$("#postList").append(postView);
				});
	            
	        }
		}
		});
});
// textarea focus일 때 작성폼 열기 끝
// infinite scroll 구현
/*var page = 4;
$(window)
		.scroll(
				function() {
					if ($(window).scrollTop()+$(window).height() == $(document).height()) {
						for(var z=0; z<6; z++){
						$('.post_view_box')
								.append(
										'<li class="infinite_scroll">'
										+'<h3>'
										+ page++
										+'번포스트</h3>'
										+'<hr> 테스트<br>테스트<br>테스트<br>테스트<br>테스트<br>테스트<br>테스트<br>'
										+'<hr>'
										+'<div class="reactBtn">'
										+"<div class='heart'></div>"
										+'<div class="share_out" onclick="openLayer("layerPop",200,18)"></div>'
										+'</div>'
										+'<div class="commentForm">'
										+'<textarea rows="1" cols="1" name="text" placeholder="댓글쓰기" class="comment_textarea"></textarea>'
										+'</div>'
										+'</li>'
										)}
					}
				});*/
// infinite scroll 끝
// Like 버튼 활성화
/*$(function(){
	$("ol").on('click','.heart', function(){
		$(this).toggleClass('is_animating');
	});
	$("ol").on('animationend','.heart', function(){
		$(this).toggleClass('is_animating');
		$(this).toggleClass('bg-position');
	});
});*/
// Like 버튼 활성화 끝
// DimLayer 
function dEI(elementID){
	return document.getElementById(elementID);
}
// 레이어 팝업 열기
function openLayer(IdName, tpos, lpos){
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
function closeLayer( IdName ){
	var pop = dEI(IdName);
	pop.style.display = "none";
	var clearEl=parent.dEI("deemed");
	var momEl = parent.dEI("wrapper");
	momEl.removeChild(clearEl);
}
// DimLayer 끝
// 글 내용 없을 때 작성버튼 비활성화
$(function(){
	if($('.type_choice_textarea').val()==''){
		$('post_submit_btn').prop('disabled',true);
		$('post_submit_btn').css('opacity',0.5); 
	}else{
		$('post_submit_btn').prop('disabled',false);
		$('post_submit_btn').css('opacity',1); 
	}
});
// 글 내용 없을 때 작성버튼 비활성화 끝

// Post Layout Build
function buildPost(postData){
	console.log(postData);
	var heart = "<div class='heart' id='heart-"+postData.userid+"'></div>";
	var shareOut = "<div class='share_out' onclick='openLayer('layerPop',200,18)'></div>";
	var comment = "<form action='commentWrite'>" + 
		"<div class='commentForm'><input type='hidden' value='"+userid+"' name='userid'>"+
		"<textarea row='1' cols='1' name='commentText' placeholder='댓글쓰기' class='comment_textarea'></textarea>"+
		"<button class='commentBtn'>입력</button>"+
		"<input type='hidden' value='"+postData.pid+"' name='commentPid'>" +
		"</div></form>";
	
	var postView = "<li class='infinite_scroll'>" + postData.userid + "<hr>";
	if(postData.files != null){ // if attached images or videos exist 
		postView = postView + "<img src='"+postData.files[0]+"'>"
	}
	if(postData.text != null){
		postView = postView + "<h3>" + postData.text + "</h3>"
	}
	postView = postView + "<div class='reactBtn'>" + heart + shareOut+ "</div>";
	postView = postView + comment;
	postView = postView + "</li>";
	
	
	return postView;
}