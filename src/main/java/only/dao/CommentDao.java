package only.dao;

import java.util.List;

import only.model.Comments;

public interface CommentDao {

	int insert(Comments comment);

	List<Comments> getComments(int ref_id, int ref_type, int pageNum);

	int getLikesCount(int lid);

	int getCommentCount(int ref_id, int re_level);

}
