package only.service;

import java.util.List;

import only.model.Comments;

public interface CommentService {

	int insert(Comments comment);
	List<Comments> getComments(int ref_id, int i);
}
