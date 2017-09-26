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

	function getPostImage() {
		/*const hash = Math.floor(Math.random() * Number.MAX_SAFE_INTEGER);
		const image = new Image;
	image.className = 'article-list__item__image article-list__item__image--loading';
	image.src = 'http://api.adorable.io/avatars/250/' + hash;
	image.onload = function() {
		image.classList.remove('article-list__item__image--loading');
	};
	
	return post;*/
}

function getPost() {
	const articleImage = getArticleImage();
	const article = document.createElement('article');
	article.className = 'article-list__item';
	article.appendChild(articleImage);
	
	return post;
}

function getPostPage(page, postsPerPage = 10) {
	console.log("getPostPage called");
	$.post('loadPost', "userid=" + userid +"&pageNum="+page, function(data) {
		
		console.log("returnede from loadPost")
		if (data == null) {
			console.log("no data to append");
			$("#postList").html("No Post");
		} else {
			console.log("data to append");
			var postView;
			for (var i = 0; i < data.length; i++) {
				console.log(postView);
				postView = buildPost(data[i]);
				$("#postList").append(postView);
			}
		}
	});
}

/*function addPaginationPage(page) {
	const pageLink = document.createElement('a');
	pageLink.href = '#' + getPageId(page);
	pageLink.innerHTML = page;
	
	const listItem = document.createElement('li');
	listItem.className = 'article-list__pagination__item';
	listItem.appendChild(pageLink);
	
	articleListPagination.appendChild(listItem);
	
	if (page === 2) {
		articleListPagination.classList.remove('article-list__pagination--inactive');
	}
}*/

function fetchPage(page) {
	//console.log("getPostPage Result:" + getPostPage(page));
	//postList.append(getPostPage(page));
}

function addPage(page) {
	getPostPage(page);
	//fetchPage(page);
	//addPaginationPage(page);
}

});


