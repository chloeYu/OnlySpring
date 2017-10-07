package only.service;

import java.util.List;

import only.model.Comments;

public interface CommentService {

	int insert(Comments comment);
	int getLikesCount(int lid);
	List<Comments> getComments(int ref_id, int ref_type, int i);
	int getCommentCount(int ref_id, int re_level);
}
