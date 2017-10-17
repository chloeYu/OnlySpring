var isLastPage = [false, false, false, false]; // timeline, blog, friends, photos
$(function() {
	$('body').on('click', '.sendBtn', function(event){
		var ref_type = 0; // 0 post / 1 comment
		if(event.target.id.split('-')[1]=='comment'){
			ref_type = 1;
		}
		var ref_id = event.target.id.split('-')[2];
		console.log('#commentText-post-'+ref_id);
		
		console.log(ref_id + ", " + text);
		var text = "";
		if(ref_type == 0){
			text = $('#commentText-post-'+ref_id).val();
		} else{
			text = $('#commentText-comment-'+ref_id).val();
		}
		if(text==null || text==""){
			alert("댓글을 입력하세요");
			return;
		}
		
		var $this = $(this);
		$this.removeClass('sendBtn').addClass('disableSendBtn');
		$.post('/only/writeComment', "ref_id="+ref_id+"&text="+text+"&ref_type="+ref_type, function(data){
			
			alert("댓글 작성 성공");
			if(ref_type==0){
				$('#commentText-post-'+ref_id).val("");
				$('#commentView-'+ref_id).html(data);
			}
			else{
				$('#commentView-comment-'+ref_id).children('div').remove();
				$('#commentText-comment-'+ref_id).val("");
				$('#commentView-comment-'+ref_id).append(data);
				if($('#comment-comment-'+ref_id).css('display')!='none'){
					$('#comment-comment-'+ref_id).hide();
				}
			}
			$this.removeClass('disableSendBtn').addClass('sendBtn');
		});
	});
	
	$('body').on('click', '#friendTagBtn', function(){
		console.log("friendTagBtn clicked");
		if($('#addFriend').css('display')=='none'){
			$('#addFriend').show();	
		}else{
			$('#addFriend').hide();
		}
	})
	
	const postList = document.getElementById('postList');
/*	const postListPagination = document.getElementById('post-list-pagination');*/
	let page = 0;
	
	addPage(++page);
	window.onscroll = function() {
	if (getScrollTop() < getDocumentHeight() - window.innerHeight) return;
		console.log("load more posts..");
		addPage(++page);
	};

	function getDocumentHeight() {
		const body = document.body;
		const html = document.documentElement;
	
		return Math.max(
				body.scrollHeight, body.offsetHeight,
				html.clientHeight, html.scrollHeight, html.offsetHeight
		);
	};

	function getScrollTop() {
		return (window.pageYOffset !== undefined) ? window.pageYOffset : (document.documentElement || document.body.parentNode || document.body).scrollTop;
	}

function getPostPage(page) {
	console.log(window.location.host);
	if(window.location.href.indexOf('http://' + window.location.host + '/only/timeline') > -1){
		if(!isLastPage[0]){
			$.post('/only/loadPost', "userid=" + userid +"&pageNum="+page, function(data) {
				if (data.trim() == "" && page == 1) {
					$("#postList").append("No Post");
					isLastPage[0] = true;
				} else if(data.trim() == "" && page > 1){
					$("#postList").append("<li style='text-align:center; font-weight:bold;font-style: italic; margin-bottom: 30px;'><h3>No More Post</h3></li>");
					isLastPage[0] = true;
				}
				else {
					$("#postList").append(data);
				}
				var cList = $('.commentView');
				$.each(cList, function(key, value){
					var ref_id = cList[key].id.split('-')[1];
					var ref_type = 0; // 0 : post
					$.post('/only/loadComment', "ref_id="+ref_id+"&ref_type=0&pageNum=1", function(cdata){
						$("#"+cList[key].id).html(cdata);
					});
				});
				$('.infinite_scroll .postImg3:nth-child(6)').on('click',function(e){
					if(!$('.det').hasClass('show')){
						$(this).parent().children('.det').addClass('show').removeClass('hide');
					}
//					init();
				});
			});
		}
	} else if(window.location.href.indexOf('http://' + window.location.host + '/only/blog') > -1  && window.location.href.indexOf('friendList') < 0 && window.location.href.indexOf('photos') < 0){
		if(!isLastPage[1]){
			var url = window.location.href.split('/');
			var owner = url[url.length-1];
			console.log(owner);
			$.post('/only/loadBlog', "ownerid=" + owner +"&userid="+userid+"&pageNum="+page, function(data) {
				/*console.log("returned from loadPost<start>" + data.trim()+"<end>" );*/
				if (data.trim() == "" && page == 1) {
					$("#postList").append("No Post");
					isLastPage[0] = true;
				} else if(data.trim() == "" && page > 1){
					$("#postList").append("<li style='text-align:center; font-weight:bold;font-style: italic; margin-bottom: 30px;'><h3>No More Post</h3></li>");
					isLastPage[0] = true;
				}
				else {
					console.log(data)
					$("#postList").append(data);
				}
				var cList = $('.commentView');
				$.each(cList, function(key, value){
					var ref_id = cList[key].id.split('-')[1];
					var ref_type = 0; // 0 : post
					console.log(key+": " + ref_id);
					$.post('/only/loadComment', "ref_id="+ref_id+"&ref_type=0&pageNum=1", function(cdata){
						console.log(cdata);
						$("#"+cList[key].id).html(cdata);
					});
				});
				$('.infinite_scroll .postImg3:nth-child(6)').on('click',function(e){
					if(!$('.det').hasClass('show')){
						$(this).parent().children('.det').addClass('show').removeClass('hide');
					}
//					init();
				});
			});
		}
	} else if(window.location.href.indexOf('http://' + window.location.host + '/only/blog') > -1 && window.location.href.indexOf('friendList') > -1){
		if(!isLastPage[2]){
			var url = window.location.href.split('/');
			var owner = url[url.length-2];
			console.log(owner);
			$.post('/only/appendFriendList', "owner="+owner+"&pageNum="+page, function(data) {
				if (data.trim() == "" && page == 1) {
					$("#friendListDisplay").append("No Friend");
					isLastPage[2] = true;
				} else if(data.trim() == "" && page > 1){
					$("#friendListDisplay").append("<li style='text-align:center; font-weight:bold;font-style: italic; margin-bottom: 30px;'><h3>No More Friend</h3></li>");
					isLastPage[2] = true;
				}
				else {
					console.log(data)
					$("#friendListDisplay").append(data);
				}				
			});
		}
	}
	else if(window.location.href.indexOf('http://' + window.location.host + '/only/blog') > -1 && window.location.href.indexOf('photos') > -1){
		if(!isLastPage[3]){
			var url = window.location.href.split('/');
			var owner = url[url.length-2];
			console.log(owner);
			$.post('/only/appendPictureList', "owner="+owner+"&pageNum="+page, function(data) {
				if (data.trim() == "" && page == 1) {
					$("#photoListDisplay").append("No Photo");
					isLastPage[3] = true;
				} else if(data.trim() == "" && page > 1){
					$("#photoListDisplay").append("<li style='text-align:center; font-weight:bold;font-style: italic; margin-bottom: 30px;'><h3>No More Photos</h3></li>");
					isLastPage[3] = true;
				}
				else {
					console.log(data)
					$("#photoListDisplay").append(data);
				}				
			});
		}
	}
}

	function addPage(page) {
		getPostPage(page);
	}
//});
//$(function() {
	function init() {
	    var length = $('.detailDim div').length / 2;
	    var min = $('.detailDim div').length;
	
      //find the first img and assign it to active
      $('.detailDim').find('div').first().addClass('ic_active');
      $('.detailDim').find('div').first().find('img').addClass('mainimg');
      
      //Divide Imgs in half and take the second half and move them to top of stack for wrap effect
      var getHalf = Math.ceil(length);
      var wrapImgs = $('.detailDim').find('div').slice(getHalf, min);
      $('.detailDim').prepend(wrapImgs);
      infinite();
	}
//	init();
	function infinite() {
	    //Center first image
	    var active = $('.ic_active');
//	    initActive(active);
	
	    $('.ic_active').next().addClass('next');
	    $('.ic_active').prev().addClass('prev');
	    
	    //Get title
	    var title = $('.ic_active').find('img').attr('title');
	    //Add title
	    $('.ic_title').append('<p>' + title + '</p>');
	
	    function clickMe() {
	      //prevent multiple clicks on the same img
	      var clicked = $(this);
	      clicked.unbind('click');
	
	      if(clicked.hasClass('back_btn')){
	
	        var bkbtn = clicked.parent().find('.detailDim').find('.prev');
	        clicked = bkbtn;
	
	
	      }else if (clicked.hasClass('next_btn')){
	
	        var nxbtn = $(this).parent().find('.detailDim').find('.next');
	        clicked = nxbtn;
	      }
	
	      var state;
	      var parentMarginLeftCalculation;
	      var animationCalculation;
	
	      // Checking for what was clicked and assigning state a value
	      if (clicked.hasClass('next')) {
	        state = 'next';
	      } else if (clicked.hasClass('prev')) {
	        state = 'prev';
	      }else{
	        return;
	      }
	 
	       //Sets new active img
	      toggle(clicked);
	      //Grab position, widths of necessary elements, and title 
	      elementInfo(clicked);
	
	       // Checking for what was clicked for moving first/last img
	      if (state === 'next') {
	        clicked.parent().append('<div class="postImg4">' + firstImg + '</div>');
	        clicked.parent().find('div.postImg4').first().remove();
	      } else {
	        clicked.parent().prepend('<div class="postImg4">' + lastImg + '</div>');
	        clicked.parent().find('div.postImg4').last().remove();
	      }
	
	      var parentWidth = clicked.parent().parent().width() / 2;
	      var value = (parentWidth - activeImgPositionLeft) - halfOfActiveImgWidth;
	
	     // Calculations to move images forward or backward
	      if (state === 'next') {
	
	        parentMarginLeftCalculation = value + firstImgWidth + prevImgWidth;
	        animationCalculation = parentMarginLeftCalculation - prevImgWidth;
	      } else if (state === 'prev'){
	        parentMarginLeftCalculation = value - lastImgWidth - nextImgWidth;
	        animationCalculation = parentMarginLeftCalculation + nextImgWidth;
	      }
	      
	      // Setting Margin Left based on calculations
	      clicked.parent().css('margin-left', '' + parentMarginLeftCalculation + 'px');
	
	      // Animating Margin Left
	      clicked.parent().animate({
	        marginLeft: '' + animationCalculation + ''
	      }, 1000, function() {
	        $('.next').unbind('click').click(clickMe);
	        $('.prev').unbind('click').click(clickMe);
	        $('.back_btn').unbind('click').click(clickMe);
	        $('.next_btn').unbind('click').click(clickMe);
	      });
	    }//end clickMe
	
	    //getting position, width, title and contents of the first and last img
	    function elementInfo(myThis) {
	      activeImgPositionLeft = myThis.position().left;
	      halfOfActiveImgWidth = myThis.width() / 2;
	      prevImgWidth = myThis.prev().width();
	      nextImgWidth = myThis.next().width();
	      title = myThis.find('img').attr('title');
	      myThis.parent().next().find('p').html(title);
	      firstImgWidth = myThis.parent().find('div.postImg4').first().width();
	      lastImgWidth = myThis.parent().find('div.postImg4').last().width();
	      firstImg = myThis.parent().find('div.postImg4').first().html();
	      lastImg = myThis.parent().find('div.postImg4').last().html();
	    }//end elementInfo
	
	    // adding and removing appropriate classes
	    function toggle(myThis) {
	      myThis.parent().find('img').removeClass('mainimg');
	      myThis.parent().find('div').removeClass('ic_active');
	      myThis.parent().find('div').removeClass('next');
	      myThis.parent().find('div').removeClass('prev');
	      myThis.find('img').addClass('mainimg');
	      myThis.addClass('ic_active');
	      myThis.next().addClass('next');
	      myThis.prev().addClass('prev');
	    }//end toggle
	
		$('.next').click(clickMe);
		$('.prev').click(clickMe);
		$('.back_btn').click(clickMe);
		$('.next_btn').click(clickMe);
	
		}
//		function initActive(myActive){
//			p = myActive.position().left;
//			w = myActive.width() / 2;
//			pw = myActive.parent().parent().width() / 2;
//			val = (pw - p) - w;
//			myActive.parent().css('margin-left', '' + val + 'px');
//		}
});
