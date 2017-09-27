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
			$.post('postBuild', "post="+data, function(postView){
				
			});
		}
	});
}

	function addPage(page) {
		getPostPage(page);
	}
});


