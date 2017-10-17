


function fa_heart_click(event){
	console.log(event);
	console.log(event.target.id);
	var idParse = event.target.id.split('-');
	var lid = idParse[2];
	var userid = idParse[1];
	var ltype = 0;
	console.log(idParse[0]);
	if(idParse[0]=='comment'){
		ltype=1;
	}
	console.log(userid + ", " + lid);
	console.log($(event.target).hasClass('active'));
	var action = 1;
	if($(event.target).hasClass('active')){
		console.log("좋아요 취소");
		action = 0;
	}
	$.post('/only/toggleLikes', "userid="+userid+"&lid="+lid+"&type="+ltype, function(data){
		console.log(data);
		$(event.target).toggleClass('on');
		console.log(ltype);
		if(ltype==0){
			$('#post-likes-'+lid).html('<span>'+data+'</span>');			
		} else if(ltype==1){
			console.log('#comment-likes-'+lid);
			$('#comment-likes-'+lid).html('<span>'+data+'</span>');
		}
	});
}

function fa_reply_click(event){
	console.log(event.target.id);
	var cid = event.target.id.split('-')[1];
	if($('#comment-comment-'+cid).css('display')=='none'){
		$('#comment-comment-'+cid).show();	
	}else{
		$('#comment-comment-'+cid).hide();
	}
}

function viewComment(event){
	console.log(event);
	console.log(event.target.id);
	var ref_id = event.target.id.split('-')[1];
	var ref_type = event.target.id.split('-')[2]; // 0 post / 1 comment
	var pageNum = event.target.id.split('-')[3];
	
	$.post('/only/loadComment', "ref_id="+ref_id+"&ref_type="+ref_type+"&pageNum="+pageNum, function(data){
		console.log(data);
		if(ref_type==0){
			$('#'+event.target.id).hide();
			$('#commentView-'+ref_id).append(data);
		}else{
			$('#'+event.target.id).hide();
			$('#commentView-comment-'+ref_id).append(data);
		}
	});
}