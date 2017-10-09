var userid = 0;

function searchChk() {
	if (document.frm.searchTerm.value == "") {
		alert("검색어를 입력하세요");
		return false;
	}
}

$(function(){
	/*$("#friendTagInput").on('input', function () {
		var val = this.value;
	    if($('#friendTagList').find('option').filter(function(){
	        return this.value.toUpperCase() === val.toUpperCase();        
	    }).length) {
	    	var id = $("#friendTagList").children(":selected").attr("id");
	    	console.log(id);
	    	addedFriends.push(this.value);
	    	console.log(addedFriends);
	        //send ajax request
	    	console.log(this.id);
	    	console.log(this);
	        alert(this.id);
	        $("#selectedfriends > span:last-child").before("<span class='friendTagSpan'>"+this.value+"<a><div class='removeTagBtn'></div></a></span>");
	        $("#friendTagInput").val('');
	    	$("#friendTagList").html('');

	    }
	});*/
	
	$("#friendTagList").change(function() {
		  var id = $(this).children(":selected").attr("data");
		  var text = $(this).children(":selected").text();
		  console.log(id);
		  console.log(text);
		  
		  $("#selectedfriends > span:last-child").before("<span data-userid='"+id+"' class='friendTagSpan'>"+this.value+"<a><div class='removeTagBtn'></div></a></span>");
	      $("#friendTag").append("<input type='hidden' name='taggedFriend' id='taggedFriend-"+id+"' value='"+id+"'>");
		  $("#friendTagInput").val('');
	      $("#friendTagList").html('');
	      $(".bloc").hide();
	      addedFriends.push(id);
	      console.log(addedFriends);
	      lastKeyword[1] = '';
	});
	
	$('body').on('click', '.removeTagBtn', function(event){
		console.log($(event.target).parents('span'));
		console.log($(event.target).parents('span').text());
		var id = $(event.target).parents('span').attr('data-userid');
		$(event.target).parents('span').remove();
		console.log(id);
		addedFriends = jQuery.grep(addedFriends, function(value) {
			  return value != id
			});
		$('#taggedFriend-'+id).remove();
	});
	
});
$(document).ready(
		function() {
			/* 검색결과 오른쪽 마우스 이벤트 등록 */
			if ($(".test").addEventListener) {
			/*
			 * $(".test").addEventListener('contextmenu',
			 * function(e) { e.preventDefault(); alert("You've tried
			 * to open context menu"); var userid =
			 * $(".test").attr("data-userid");
			 * document.getElementById("rmenu"+userid).className =
			 * "show";
			 * document.getElementById("rmenu"+userid).style.top =
			 * mouseY(event) + 'px';
			 * document.getElementById("rmenu"+userid).style.left =
			 * mouseX(event) + 'px'; }, false);
			 */
				} else {
					// document.getElementById("test").attachEvent('oncontextmenu',
					// function() {
					$(".test").bind('contextmenu',function(e) {
						var _target = e.target.parentNode.id;
						_target = _target.substr(5);
						var followId = "#followText-"+_target;
						/*console.log(_target);*/
						var sendData = "userid2=" + _target;
						$.post('followChk.jsp', sendData, function(data) {
							console.log("data true: "+ data.indexOf("true"));
							console.log("data false: "+ data.indexOf("false"));
							if (data.indexOf("true") > 0) {
								console.log("unfollow");
								$(followId).text("Unfollow");
								} else if (data.indexOf("false") > 0) {
									console.log("follow");
									$(followId).text("Follow");
									}
							});
						e.preventDefault();
						var rmenu = document.getElementsByName("rmenu");
						var i;
						for (i = 0; i < rmenu.length; i++) {
							// alert(rmenu[i].id);
							rmenu[i].className = "hide";
							}
						/*console.log("rmenu-"+_target);*/
						document.getElementById("rmenu-"+ _target).className = "show";
						document.getElementById("rmenu-"+ _target).style.top = mouseY(event)+ 'px';
						document.getElementById("rmenu-"+ _target).style.left = mouseX(event)+ 'px';
						});
					}

					/*
					 * function sendId() {
					 * 
					 * 
					 * function displayResult(data) { var listView =
					 * document.getElementById('checkMsg'); if (data == 0) {
					 * console.log("사용가능"); listView.innerHTML = "사용할 수 있는
					 * ID에요"; listView.style.color = "#4a76b2";
					 * $('#signup_fin').prop('disabled', false);
					 * $('#signup_fin').css('background-color', '#97a6bf');
					 * $('#signup_fin').mouseover(function() {
					 * $(this).css('background-color', '#3f5068'); });
					 * $('#signup_fin').mouseout(function() {
					 * $(this).css('background-color', '#97a6bf'); });
					 * btnDisabled(); } else { console.log("사용불가");
					 * listView.innerHTML = "이미 다른분이 사용중인 ID에요";
					 * listView.style.color = "#ff4242";
					 * $('#signup_fin').prop('disabled', true);
					 * $('#signup_fin').css('background-color', '#ff7070'); } }
					 */
			});

// this is from another SO post...
$(document).bind("click", function(event) {
	/* document.getElementById("rmenu").classNrunSame = "hide"; */
	var rmenu = document.getElementsByName("rmenu");
	var i;
	for (i = 0; i < rmenu.length; i++) {
		// alert(rmenu[i].id);
		rmenu[i].className = "hide";
	}
});

function mouseX(evt) {
	if (evt.pageX) {
		return evt.pageX;
	} else if (evt.clientX) {
		return evt.clientX
				+ (document.documentElement.scrollLeft ? document.documentElement.scrollLeft
						: document.body.scrollLeft);
	} else {
		return null;
	}
}

function mouseY(evt) {
	if (evt.pageY) {
		return evt.pageY;
	} else if (evt.clientY) {
		return evt.clientY
				+ (document.documentElement.scrollTop ? document.documentElement.scrollTop
						: document.body.scrollTop);
	} else {
		return null;
	}
}

// 검색 자동완성
// var check = false;
var lastKeyword = ["", ""];
// var loopSearchKeyword = false;
var searchType = 0; // 0: 검색창 1: FriendTag
var addedFriends = [];

function runSearch(event) {
	if(event.target.id=='friendTagInput'){
		searchType = 1;
	} else{
		searchType = 0;
	}
	//if (check == false) {
	sendSearch();
	//	setTimeout("sendSearch();", 200);
	//	loopSearchKeyword = true;
	//}
	//check = true;
}
function sendSearch() {
	var keyword = '';
	//if (loopSearchKeyword == false) {
	//	return;
	//}
	if(searchType == 0){
		console.log("searchtype: " + searchType);
		keyword = $('.searchTerm').val();
	} else if (searchType == 1){
		keyword = $('#friendTagInput').val();
	}
	console.log("keyword:" + keyword);
	if (keyword == '') {
		lastKeyword[searchType] = '';
		if(searchType==1){
			$("#friendTagList").html('');
		    $(".bloc").hide();
		}
	} else if (keyword != lastKeyword[searchType]) {
		lastKeyword[searchType] = keyword;
		if (keyword != '') {
			var params = "searchTerm=" + encodeURIComponent(keyword);
			console.log(params);
			$.ajax({
				url : "search",
				type : "POST",
				data : params,
				success : function(data) {
					var results = [];
					var size = 0;
			        for (var i = 0; i < data.length; i++) {
			            var u = {
			                userid: data[i].userid + "",
			                username: data[i].username,
			                email: data[i].email
			            };
			            if (addedFriends.indexOf(data[i].userid) < 0){
			            	size++;
			            	results[i] = "<option data='"+data[i].userid+"'>"+data[i].username+"</option><br>";
			            }
			        }
					console.log(results);
					if(searchType == 0){
						$("#searchResult").html(results);						
					} else if(searchType ==1){
						if(size > 10) size = 10;
						if(size == 1) size = 2;
						var p = $("#friendTagInput");
						var offset = p.offset();
						console.log(offset.left + ", " + offset.bottom);
						$("#friendTagList").attr('size', size+1);
						$(".bloc").show();
		            	//$("#friendTagList").offset({ top: offset.bottom, left: offset.left});
		            	$(".bloc").offset({ top: offset.bottom, left: offset.left});
		            	$("#friendTagList").html(results);
//		            	$("#friendTagList").prop("selectedIndex", -1);
		            	$("#friendTagList").attr('size', size);
		            	if(data == null || data == ''){
		            		$(".bloc").hide();
		            	}
					}
				},
				error : function() {
					console.log("자동완성기능 에러");
				}
			});
		} else {
		}
	}
	//setTimeout("sendSearch();", 200);
}


