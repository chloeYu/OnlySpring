package only.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import only.model.Comments;

@Repository
public class CommentDaoImpl implements CommentDao {
	final int COMMENTPERPAGE = 5;
	
	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public int insert(Comments comment) {
		return sst.insert("commentsns.insert", comment);
	}

	@Override
	public List<Comments> getComments(int ref_id, int pageNum) {
		return sst.selectList("commentsns.list", ref_id, new RowBounds((pageNum-1)*COMMENTPERPAGE, COMMENTPERPAGE));	
	}
}
