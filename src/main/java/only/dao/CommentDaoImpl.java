package only.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import only.model.Comments;

@Repository
public class CommentDaoImpl implements CommentDao {
	final int COMMENTPERPAGE = 4;
	
	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public int insert(Comments comment) {
		return sst.insert("commentsns.insert", comment);
	}

	@Override
	public List<Comments> getComments(int ref_id, int ref_type, int pageNum) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("ref_id", ref_id);
		map.put("re_level", ref_type);
		return sst.selectList("commentsns.list", map, new RowBounds((pageNum-1)*COMMENTPERPAGE, COMMENTPERPAGE));	
	}

	@Override
	public int getLikesCount(int lid) {
		return sst.selectOne("commentsns.getLikesCount", lid);
	}

	@Override
	public int getCommentCount(int ref_id, int re_level) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("ref_id", ref_id);
		map.put("re_level", re_level);
		return sst.selectOne("commentsns.getCommentCount", map);
	}
}
