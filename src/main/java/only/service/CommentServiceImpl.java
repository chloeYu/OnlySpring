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
	public List<Comments> getComments(int ref_id, int pageNum) {
		return cd.getComments(ref_id, pageNum);
	}
}
