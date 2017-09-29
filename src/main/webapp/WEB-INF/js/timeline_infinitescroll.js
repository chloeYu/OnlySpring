var isLastPage = false;
$(function() {
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
	console.log("getPostPage called");
	if(!isLastPage){
		$.post('loadPost', "userid=" + userid +"&pageNum="+page, function(data) {
			console.log("returned from loadPost<start>" + data.trim()+"<end>" )
			if (data.trim() == "" && page == 1) {
				$("#postList").append("No Post");
				isLastPage = true;
			} else if(data.trim() == "" && page > 1){
				$("#postList").append("<li style='text-align:center; font-weight:bold;font-style: italic; margin-bottom: 30px;'><h3>No More Post</h3></li>");
				isLastPage = true;
			}
			else {
				console.log(data)
				$("#postList").append(data);
			}
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

	function addPage(page) {
		getPostPage(page);
	}
});


function writePost(e){
	var pid = e.target.id.split('-')[1];
	var text = $('#commentText-'+pid).val();
	console.log(pid);
	if(text==null || text==""){
		alert("댓글을 입력하세요");
	}
	$.post('writeComment', "pid="+pid+"&text="+text, function(data){
		alert("댓글 작성 성공");
		console.log(data);
		$('#commentView-'+pid).html(data);
	});
}