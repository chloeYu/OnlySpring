var isLastPage = [false, false];
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
					console.log("popup!");
					 var detailContainer = '<div class="det"></div>';
					 var appendDetail = '<div class="imgDetail" style="position:fixed; z-index:100; top:0; left:0; width:100%; height:100%;">'
						 +'<div class="dimBackground" style="position:absolute; background-color:#000; opacity:0.5; width:100%; height:100%; top:0; left:0;">'
						 +'</div>'
						 +'<div class="detailDim" style="position:absolute; top:50%; left:20%; width:38%; height:auto; background-color:#FFF; opacity:1; z-index:10;">'
						 +'<div class="postImg4">'
						 +'<img class="postInner4" src="img_timeline/'+ +'">'
						 +'</div>'
						 +'</div>'
						 +'</div>';
					 
					 var imgPr = $(this);
					 $(this).parent().append(detailContainer);
					 $('.det').append(appendDetail);
					 layer_popup(imgPr);
				});
			});
		}
	} else if(window.location.href.indexOf('http://' + window.location.host + '/only/blog') > -1){
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
					console.log("popup!");
					 var detailContainer = '<div class="det"></div>';
					 var appendDetail = '<div class="imgDetail" style="position:fixed; z-index:100; top:0; left:0; width:100%; height:100%;">'
						 +'<div class="dimBackground" style="position:absolute; background-color:#000; opacity:0.5; width:100%; height:100%; top:0; left:0;">'
						 +'</div>'
						 +'<div class="detailDim" style="position:absolute; top:50%; left:20%; width:38%; height:auto; background-color:#FFF; opacity:1; z-index:10;">'
						 +'<div class="postImg4">'
						 +'<img class="postInner4" src="img_timeline/'+ +'">'
						 +'</div>'
						 +'</div>'
						 +'</div>';
					 
					 var imgPr = $(this);
					 $(this).parent().append(detailContainer);
					 $('.det').append(appendDetail);
					 layer_popup(imgPr);
				});
			});
		}
	} 
}

	function addPage(page) {
		getPostPage(page);
	}
});

