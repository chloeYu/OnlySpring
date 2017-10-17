package only.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import only.model.Likes;

@Repository
public class LikesDaoImpl implements LikesDao {
	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public int toggleLikes(Likes l) {
		return sst.update("likesns.toggleLikes", l);
	}

	@Override
	public int isLiked(Likes l) {
		return sst.selectOne("likesns.isLiked", l);
	}
}
