package only.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import only.dao.CommentDao;
import only.model.Comments;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao cd;

	@Override
	public int insert(Comments comment) {
		return cd.insert(comment);
	}

	@Override
	public List<Comments> getComments(int ref_id, int ref_type, int pageNum) {
		return cd.getComments(ref_id, ref_type, pageNum);
	}

	@Override
	public int getLikesCount(int lid) {
		return cd.getLikesCount(lid);
	}

	@Override
	public int getCommentCount(int ref_id, int re_level) {
		return cd.getCommentCount(ref_id, re_level);
	}
}
