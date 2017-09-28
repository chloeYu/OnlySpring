$(function() {
	
	const postList = document.getElementById('postList');
/*	const postListPagination = document.getElementById('post-list-pagination');*/
	let page = 0;

	addPage(++page);
	window.onscroll = function() {
	if (getScrollTop() < getDocumentHeight() - window.innerHeight) return;
		addPage(++page);
	};
	function getPageId(n) {	
		return 'post-page-' + n;
	}

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


function getPost() {
	const articleImage = getArticleImage();
	const article = document.createElement('article');
	article.className = 'article-list__item';
	article.appendChild(articleImage);
	
	return post;
}

function getPostPage(page) {
	console.log("getPostPage called");
	$.post('loadPost', "userid=" + userid +"&pageNum="+page, function(data) {
		console.log("returnede from loadPost")
		if (data == null) {
			$("#postList").append("No Post");
		} else {
			console.log(data)
			$("#postList").append(data);
		}
	});
	
	console.log("add js for image");
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
}

	function addPage(page) {
		getPostPage(page);
	}
});


